import java.util.Random;
import java.util.Scanner;



/*===========NO NEED=====================*/


class player{
	private char symbol;
	private int location[];
	int i;
	public player(char sym){
		symbol = sym;
		location = new int[5];
	}
	public char getSymbol(){
		return symbol;
	}
	public int[] getLocation(){
		return location;
	}
	public void addLocation(int loc){
		location[i]=loc;
		i++;
	}
	
}

class board{
	player p1; 
	player p2; 
	private char positions[];
	char empty = '_';
	Random random = new Random();
	public board(){
		p1 = new player('X');
		p2 = new player('0');
		positions = new char[9];
		for (int i = 0; i < positions.length; i++) {
			positions[i] = empty;
		}
		
	}
	
	/***Set place after giving player and the position**/
	public void setMove(player p,int location){
		if(positions[location]==empty){
			positions[location]= p.getSymbol();
			p.addLocation(location);
		}
		else{
			Scanner scn = new Scanner(System.in);
			setMove(p, scn.nextInt()-1);
		}
		
	}

	
	/***Make the movement auto by the machine***/
	public void AutoMove(player p,String level){
		int j='\0';
		if(level.equalsIgnoreCase("easy")){
			do{
				 j = random.nextInt(9);
			}while(positions[j]!=empty);
			setMove(p, j);
		}
		
	
	}

	/****Check whether game is winning***/
	public boolean checkWinning(){
		boolean state = false;
			
		for(int i=0;i<3;i++){
			if(positions[i]==positions[i+3] &&  positions[i+3]==positions[i+6] && positions[i]!=empty ){
				state= true;
				break;
				
			}
			else if(positions[3*i]==positions[3*i+1] && positions[3*i+1]==positions[3*i+2] && positions[3*i]!=empty){
				state= true;
				break;
			}
			else if(positions[0]==positions[4] && positions[4]==positions[8]&& positions[0]!=empty){
				state= true;
				break;
			}
			else if(positions[2]==positions[4] && positions[4]==positions[6]&& positions[2]!=empty){
				state = true;
				break;
			}
			
		}
		return state;
	}
}