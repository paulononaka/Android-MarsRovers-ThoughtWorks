package com.thoughtworks.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.thoughtworks.R;
import com.thoughtworks.constants.Constant;
import com.thoughtworks.entities.Coordenate;
import com.thoughtworks.entities.Position;
import com.thoughtworks.entities.Rover;
import com.thoughtworks.enums.Command;
import com.thoughtworks.enums.Orientation;

public class ThoughtWorksActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Command[] commandsRoverA = new Command[] { 
				Command.L, 
				Command.M, 
				Command.L, 
				Command.M, 
				Command.L, 
				Command.M,
				Command.L, 
				Command.M, 
				Command.M };
		
		final Command[] commandsRoverB = new Command[] { 
				Command.M, 
				Command.M, 
				Command.R, 
				Command.M, 
				Command.M, 
				Command.R,
				Command.M, 
				Command.R, 
				Command.R,
				Command.M};
		
		Button btnStartRoverA = (Button) findViewById(R.id.btnStartRoverA);
		btnStartRoverA.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				drawPlateau();
				
				Rover roverA = new Rover(new Position(new Coordenate(1, 2), Orientation.N));
				new ThreadDraw(roverA, commandsRoverA).start();
			}
		});
		
		Button btnStartRoverB = (Button) findViewById(R.id.btnStartRoverB);
		btnStartRoverB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				drawPlateau();
				
				Rover roverB = new Rover(new Position(new Coordenate(3, 3), Orientation.E));
				new ThreadDraw(roverB, commandsRoverB).start();
			}
		});
	}

	private void drawPlateau() {

		TableLayout plateau = (TableLayout) findViewById(R.id.plateau);
		plateau.removeAllViews();

		for (int y = Constant.UPPER_MAX; y >= 0; y--) {
			TableRow tr = new TableRow(this);

			for (int x = 0; x <= Constant.RIGHT_MAX; x++) {
				ImageView iv = new ImageView(this);
				iv.setId(y * 10 + x);
				iv.setBackgroundResource(R.drawable.tile);
				tr.addView(iv);
			}
			plateau.addView(tr);
		}
	}

	class ThreadDraw extends Thread {

		private Rover rover;
		private Command[] commands;

		public ThreadDraw(Rover rover, Command[] commands) {
			this.rover = rover;
			this.commands = commands;
		}

		@Override
		public void run() {

			move(null);

			for (Command command : commands) {
				move(command);
			}
		}

		private void move(Command command) {
			Message msg = handler.obtainMessage();
			msg.getData().putSerializable("rover", rover);
			msg.getData().putSerializable("command", command);
			handler.sendMessage(msg);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
	}

	final Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			Rover rover = (Rover) msg.getData().getSerializable("rover");
			Command command = (Command) msg.getData().getSerializable("command");

			Position position;
			ImageView tile;

			if (command != null) {
				position = rover.getPosition();
				tile = getTile(position);
				tile.setBackgroundResource(R.drawable.tile);
				rover.move(command);
			}

			position = rover.getPosition();
			tile = getTile(position);
			tile.setBackgroundResource(getRoverResource(position.getOrientation()));
			
			TextView txtPosition = (TextView) findViewById(R.id.txtPosition);
			txtPosition.setText("Position: " + position.toString());
		}
	};

	private ImageView getTile(Position position) {
		int x = position.getCoordenate().getX();
		int y = position.getCoordenate().getY();
		return (ImageView) findViewById(y * 10 + x);
	}

	private int getRoverResource(Orientation orientation) {
		if (orientation == Orientation.N) {
			return R.drawable.rover_n;
		} else if (orientation == Orientation.E) {
			return R.drawable.rover_e;
		} else if (orientation == Orientation.W) {
			return R.drawable.rover_w;
		} else if (orientation == Orientation.S) {
			return R.drawable.rover_s;
		}
		return 0;
	}

}