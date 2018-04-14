// insert header here
package assignment6;

import java.util.*;

public class Theater {
	/*
	 * Represents a seat in the theater
	 * A1, A2, A3, ... B1, B2, B3 ...
	 */
	//getters and setters can be public
	//other helpers must be private
	static class Seat {
		private int rowNum;
		private int seatNum;
		private boolean taken;
		public Seat(int rowNum, int seatNum) {
			this.rowNum = rowNum;
			this.seatNum = seatNum;
			this.taken=false;
		}
		public int getSeatNum() {
			return seatNum;
		}
		public int getRowNum() {
			return rowNum;
		}
		//DONE
		private String rowLetter(int row) {
			/*int bytesinint = 4;
			StringBuffer buffer = new StringBuffer(bytesinint);
			for(int i=bytesinint-1;i>=0;i--){
				buffer.append((char)((row>>(8*i))&0xFF));
			}
			return(buffer.toString());
			*/
			//NOTE input is not ascii input is 1-26
			//doesn't read Z's
			String letter = "";
			while(row>0){
				int rem= row%26;
				if(rem==0){
					letter+='Z';
					row=(row/26)-1;
				}
				else {
					row/=26;
					letter+=(char)(rem+64);
				}

			}
			StringBuilder build = new StringBuilder(letter);
			return((build.reverse().toString()));

		}
		//Done.. Maybe errors due to str=""+letter+seat;
		@Override
		public String toString() {
			// TODO: Implement this method to return the full Seat location ex: A1
			int row = this.getRowNum();
			int seat = getSeatNum();
			String letter = rowLetter(row);
			String str = ""+letter+seat;
			return str;
		}
	}

  /*
	 * Represents a ticket purchased by a client
	 */
	static class Ticket {
	  private String show;
	  private String boxOfficeId;
	  private Seat seat;
	  private int client;
	  private int width = 33;
	  private int height = 5;

	  public Ticket(String show, String boxOfficeId, Seat seat, int client) {
		  this.show = show;
		  this.boxOfficeId = boxOfficeId;
		  this.seat = seat;
		  this.client = client;
	  }

	  public Seat getSeat() {
		  return seat;
	  }

	  public String getShow() {
		  return show;
	  }

	  public String getBoxOfficeId() {
		  return boxOfficeId;
	  }

	  public int getClient() {
		  return client;
	  }
	  //DONE TO STRING
	  @Override
	  public String toString() {
		  StringBuilder strline1 = new StringBuilder(height);
		  StringBuilder strhash1 = new StringBuilder(width);
		  // TODO: Implement this method to return a string that resembles a ticket
		  for (int i =0; i < width; i++) {
		  	if(i<width-1) {
				strhash1.append("-");
			}
			if(i==width-1) {
				strhash1.append("-\n");
			}
		  }

		  for (int j = 0; j < height; j++) {
		  	if(j==0){
		  		int dist = width- 7-this.show.length();
		  		StringBuilder space = new StringBuilder(dist);
		  		for(int i=0; i<dist; i++){
		  			space.append(" ");
				}
		  		strline1.append("| Show: "+this.show+space+"|\n");
		  	}
		  	if(j==1){
				int dist = width- 16-this.boxOfficeId.length();
				StringBuilder space = new StringBuilder(dist);
				for(int i=0; i<dist; i++){
					space.append(" ");
				}
		  		strline1.append("| Box Office ID: "+ this.boxOfficeId+space+"|\n");
			  }
			  if(j==2){
		  		int dist = width - 7-this.seat.toString().length();
		  		StringBuilder space = new StringBuilder(dist);
		  		for(int i=0; i<dist; i++) {
					space.append(" ");
				}
		  		strline1.append("| Seat: "+this.seat+space+"|\n");
			  }
			  if(j==3){
				  int dist = width - 11;
				  StringBuilder space = new StringBuilder(dist);
				  for(int i=0; i<dist; i++) {
					  space.append(" ");
				  }
		  		strline1.append("| Client: "+this.client+space+"|\n");
			  }
			  if(j==height-1){
		  		StringBuilder space = new StringBuilder(width);
		  		for(int i=0;i<width;i++){
		  			space.append(" ");
				}
		  		strline1.append("|"+space+"|\n");
			  }
		  }

		  StringBuilder newstr= new StringBuilder(height*width);
		  newstr.append(strhash1);
		  newstr.append(strline1);
		  newstr.append(strhash1);
		  //newstr.append("\n");

		  return newstr.toString();
	  }
  }
  /*
  	Stuff for Theater. Only Seat and Ticket provided
   */
	private String Show;
	private int NumRows;
	private int SeatsPerRow;
	private Seat bestSeat;
	private List<Ticket> translog = new ArrayList<>();
	private HashMap<Integer,Seat> theatremap;
	//Done below. use Theatremap to check seats and update seat taken.
	public Theater(int numRows, int seatsPerRow, String show) {
		// TODO: Implement this constructor
		theatremap= new HashMap<Integer,Seat>();
		for(int i=1; i<=numRows;i++){
			for(int j=1; j<=seatsPerRow; j++){
				theatremap.put(i,new Seat(i,j));
			}
		}
		NumRows=numRows;
		SeatsPerRow=seatsPerRow;
		Show=show;
	}

	/*
	 * Calculates the best seat not yet reserved
	 *
 	 * @return the best seat or null if theater is full
   */
	public Seat bestAvailableSeat() {
		//TODO: Implement this method
		Iterator<Map.Entry<Integer, Seat>> iterator2 = theatremap.entrySet().iterator();

		while(iterator2.hasNext())
		{
			Map.Entry<Integer, Seat> next = iterator2.next();
			if(!next.getValue().taken){
				next.getValue().taken=true;
				return next.getValue();
			}
		}
		return null;
	}

	/*
	 * Prints a ticket for the client after they reserve a seat
   * Also prints the ticket to the console
	 *
   * @param seat a particular seat in the theater
   * @return a ticket or null if a box office failed to reserve the seat
   */
	public Ticket printTicket(String boxOfficeId, Seat seat, int client) {
		//TODO: Implement this method
		return null;
	}

	/*
	 * Lists all tickets sold for this theater in order of purchase
	 *
   * @return list of tickets sold
   */
	public List<Ticket> getTransactionLog() {
		//TODO: Implement this method
		return null;
	}
}
