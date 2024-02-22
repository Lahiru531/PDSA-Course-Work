
package hotelroomreservation;
import java.util.Scanner;

class Room
{
    int roomNumber;
    boolean Availability;
    Room left;
    Room right;
    public Room (int value)
    {
       
        this.roomNumber=value;
        this.Availability=true;
        this.left=null;
        this.right=null;
       
    }
      
}
class Hotelreservation
{  
     
    public Room addroom(Room node,int roomNumber)
    {
        if(node==null)
        {
            return new Room(roomNumber);
        }
        if(roomNumber<node.roomNumber)
        {
            node.left=addroom(node.left, roomNumber);
        }
        else if(roomNumber>node.roomNumber)
        {
            node.right=addroom(node.right, roomNumber);
        }
        return node;
    }
     
      public Room deleteRoom(Room node,int roomNumber)
      {
          if(node==null)
          {
              return null;
          }
          if(roomNumber < node.roomNumber)
          {
              node.left=deleteRoom(node.left, roomNumber);
          }
          else if(roomNumber>node.roomNumber)
          {
              node.right=deleteRoom(node.right, roomNumber);
          }
          else
          {
              if(node.left==null)
              {
                  return node.right;
              }
              else if(node.right==null)
              {
                  return node.left;
              }
             
                  node.roomNumber=getsuccessor(node.right);
                  node.right=deleteRoom(node.right, node.roomNumber);
                    
          }
          return node;
      }
      public int getsuccessor(Room node)
      {
         
          int temparary=node.roomNumber;
          while(node.left != null)
          {
              temparary=node.left.roomNumber;
              node=node.left;
          }
          return temparary;
      }
      public Room findroom(Room node,int roomNumber)
      {
          if(node==null || node.roomNumber==roomNumber)
          {
              return node;
          }
          if (roomNumber < node.roomNumber) {
            return findroom(node.left, roomNumber);
        } else {
            return findroom(node.right, roomNumber);
        }
             
      }
      public void ReservationRoom(Room node,int roomnumber,int days)
      {
          Room roomtoreserve=findroom(node, roomnumber);
          if(roomtoreserve != null && roomtoreserve.Availability)
          {   
              roomtoreserve.Availability=false;
              int Totalbill;
              if(roomnumber>node.roomNumber)
              {
                  Totalbill=10000*days;
                 
                 
              }
              else{
                  Totalbill=7500*days;
                   
              }
              System.out.println(" Room " +roomnumber+ " has been reserved");
              System.out.println(" Total cost for "+roomnumber+ " is LKR " +Totalbill);
          }
          else
          {
               System.out.println("Room " +roomnumber+ " already Reserved.");
          }
      }
      public boolean checkAvailability(Room node,int roomnumber)
      {
          Room check=findroom(node, roomnumber);
         if (check != null)
         {
        if (check.Availability)
        {
            System.out.println("Room " + roomnumber + " is available.");
            return true;
        } else
        {
            System.out.println("Room " + roomnumber + " is not available.");
            return false;
        }
      }
         else
         {
        System.out.println("Room " + roomnumber + " does not exist.");
        return false;
      }
      }
      public void display(Room node) {
        if (node == null) {
            return;
        }
        display(node.left);
        System.out.print(node.roomNumber + " ");
        display(node.right);
       
    }
      public void preorder(Room node)
      {
          if (node == null) {
            return;
        }
        System.out.print(node.roomNumber + " ");
         preorder(node.left);
         preorder(node.right);
      }
      public void postorder(Room node)
      {
           if (node == null) {
            return;
        }
           postorder(node.left);
           postorder(node.right);
           System.out.print(node.roomNumber + " ");
           
      }
     
}

public class HotelRoomReservation {

    
    public static void main(String[] args) {
         Hotelreservation hotelreservation =new Hotelreservation();
        
        Room root=null;
        root = hotelreservation.addroom(root, 101);
        root = hotelreservation.addroom(root, 102);
        root = hotelreservation.addroom(root, 105);
        root = hotelreservation.addroom(root, 103);
        root = hotelreservation.addroom(root, 100);
        System.out.println("Rooms in the hotel:");
        hotelreservation.display(root);
        System.out.println();
        //  hotelreservation.postorder(root);
       // hotelreservation.preorder(root);
         
       // root=hotelreservation.deleteRoom(root, 101);
       // System.out.println("Rooms in the hotel:");
       // hotelreservation.display(root);


        Scanner scanner = new Scanner(System.in);
        
         while(true)
         {
            System.out.println("Please enter the relevant number to get the service you need");
            System.out.println("\nOptions:");
            System.out.println("1. Add a room");
            System.out.println("2. Delete a room");
            System.out.println("3. Reserve a room");
            System.out.println("4. Total income");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    {
                        System.out.println("Enter Room to be add");
                        int room=scanner.nextInt();
                        root=hotelreservation.addroom(root, room);
                        break;
                    }
                case 2:
                    {
                        System.out.println("Enter Room to be deleted");
                        int room=scanner.nextInt();
                        root=hotelreservation.deleteRoom(root, room);
                        break;
                    }
                case 3:
                    {
                        System.out.println("Enter the Room number to be reserved ");
                        int room=scanner.nextInt();
                        if(room>root.roomNumber)
                        {
                            System.out.println("VIP Room");
                            System.out.println("Cost for VIP room LKR 10000 per day");
                            System.out.println("Enter number days ");
                            int days=scanner.nextInt();
                            hotelreservation.ReservationRoom(root, room, days);
                        }
                        else
                        {
                            System.out.println("Standard Room");
                            System.out.println("Cost for Standard room LKR 7500 per day");
                            System.out.println("Enter number days ");
                            int days=scanner.nextInt();
                            hotelreservation.ReservationRoom(root, room, days);
                           
                        }       break;
                    }
                case 4:
                    System.out.println("Maximum price on a room  on that day");                   
                    break;
                    
                case 0:
                    System.out.print("Exit programm");
                    break;
                    
                default:
                    break;
            }
           
         }
    }
    
}
