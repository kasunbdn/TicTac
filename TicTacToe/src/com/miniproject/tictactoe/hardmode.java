import java.util.Random;
import java.util.Scanner;



public class player{
	private char symbol;
	private int location[];
	int i;
	public player(char sym){
		symbol = sym;
		location = new int[5];
		i=0;
		
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
			System.out.println("Try another location");
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
		if(level.equalsIgnoreCase("medium")){
			if(checkPattern()!=10){
				setMove(p,checkPattern());
			}
			else{
				AutoMove(p, "easy");
			}
		}
		if(level.equalsIgnoreCase("hard")){
			if(HardMode(p)!=20){
				setMove(p,HardMode(p));
			}
			else if(checkPattern()!=10){
				setMove(p, checkPattern());
			}
			else{
				if(positions[4]==empty){
					setMove(p, 4);
					System.out.println("fdfdsfs");
				}
				else{
					AutoMove(p, "easy");
				}
			}
				

		}
	
	}
	/*****Analyze the board to obtain next move****/
	public int checkPattern(){
		int loc=10;
		for(int i=0;i<3;i++){
		
			if(positions[i]==positions[i+3] &&  positions[i+6]==empty && positions[i]!=empty ){
				loc = i+6;
				break;
			}
			else if(positions[i+6]==positions[i+3] &&  positions[i]==empty && positions[i+3]!=empty ){
				loc = i;
				break;
			}
			else if(positions[i]==positions[i+6] &&  positions[i+3]==empty && positions[i+6]!=empty){
				loc = i+3;
				break;
				
			}
			else if(positions[3*i]==positions[3*i+1] && positions[3*i+2]==empty&& positions[3*i]!=empty){
				loc = 3*i+2;
				break;
			}
			else if(positions[3*i+1]==positions[3*i+2] &&positions[3*i]==empty&& positions[3*i+1]!=empty){
				loc = 3*i;
				break;
			}
			else if(positions[3*i]==positions[3*i+2] &&positions[3*i+1]==empty&& positions[3*i+2]!=empty){
				loc = 3*i+1;
				break;
			}
			
			else if(positions[4]!=empty ){
				if(positions[0]==positions[4] && positions[8]==empty){
					loc = 8;
					break;
				}
				if(positions[8]==positions[4] && positions[0]==empty){
					loc = 0;
				}
				if(positions[2]==positions[4] && positions[6]==empty){
					loc = 6;
					break;
				}
				if(positions[6]==positions[4] && positions[2]==empty){
					loc = 2;
				}
			}
			else if(positions[0]!=empty ){
				if(positions[0]==positions[8] && positions[4]==empty){
					loc = 4;
					break;
				}
				
			}
			else if(positions[2]!=empty ){
				if(positions[2]==positions[6] && positions[4]==empty){
					loc = 4;
					break;
				}
				
			}
			/*------------------------------*/
		}
		return loc;
	}
	/***For Hard Mode***/
	public int HardMode(player p){
		int loc=20;
		for(int i=0;i<3;i++){
		
			if(positions[i]==positions[i+3] &&  positions[i+6]==empty && positions[i]==p.getSymbol() ){
				loc = i+6;
				break;
			}
			else if(positions[i+6]==positions[i+3] &&  positions[i]==empty && positions[i+3]==p.getSymbol() ){
				loc = i;
				break;
			}
			else if(positions[i]==positions[i+6] &&  positions[i+3]==empty && positions[i+6]==p.getSymbol()){
				loc = i+3;
				break;
				
			}
			else if(positions[3*i]==positions[3*i+1] && positions[3*i+2]==empty&& positions[3*i]==p.getSymbol()){
				loc = 3*i+2;
				break;
			}
			else if(positions[3*i+1]==positions[3*i+2] &&positions[3*i]==empty&& positions[3*i+1]==p.getSymbol()){
				loc = 3*i;
				break;
			}
			else if(positions[3*i]==positions[3*i+2] &&positions[3*i+1]==empty&& positions[3*i+2]==p.getSymbol()){
				loc = 3*i+1;
				break;
			}
			
			else if(positions[4]==p.getSymbol() ){
				if(positions[0]==positions[4] && positions[8]==empty){
					loc = 8;
					break;
				}
				if(positions[8]==positions[4] && positions[0]==empty){
					loc = 0;
				}
				if(positions[2]==positions[4] && positions[6]==empty){
					loc = 6;
					break;
				}
				if(positions[6]==positions[4] && positions[2]==empty){
					loc = 2;
				}
			}
			else if(positions[0]==p.getSymbol() ){
				if(positions[0]==positions[8] && positions[4]==empty){
					loc = 4;
					break;
				}
				
			}
			else if(positions[2]==p.getSymbol() ){
				if(positions[2]==positions[6] && positions[4]==empty){
					loc = 4;
					break;
				}
				
			}
		/*	else
				loc = checkPattern();
			/*------------------------------*/
		}
		return loc;
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