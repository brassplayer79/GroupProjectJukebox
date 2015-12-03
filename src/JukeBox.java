import java.util.LinkedList;
import java.util.List;
import java.util.Random;  //Project requirement: use java library
import java.util.Scanner;


public class JukeBox  //Project requirement: modifier - public and private are considered access modifiers
{
    private static Random randomNumberGenerator = new Random();

    public static long MILLISECONDS = 1;  //Project requirement: static variables
    public static long SECONDS = 1000*MILLISECONDS;
    public static long MINUTES = 60*SECONDS;

    // Used to display menu for adding songs
    private static final String songList[] =    //Project requirement: constant
            {
                    "Twelve Days of Christmas",
                    "Feliz Navidad",
                    "The First Noel",
                    "Santa Claus is Coming to Town",
                    "Jingle Bell Rock\n"
            };

    private List<ChristmasSong> songs; //Project requirement: Polymorphism

    //Project requirement: encapsulation - the songs that can be played and the songs that are queued up to play.
    //Only the JukeBox controls these songs

    public JukeBox() //Project requirement: Constructor
    {
        this.songs = new LinkedList<ChristmasSong>();  //Project requirement: "this" keyword
    }

    private static ChristmasSong getSong(int songNumber)
    {
        switch (songNumber)  //Project requirement: switch statement
        {
            case 0:
                return new TwelveDays();
            case 1:
                return new FelizNavidad();
            case 2:
                return new FirstNoel();
            case 3:
                return new SantaClausComingToTown();
            case 4:
                return new JingleBellRock();
            default:
                return null;
        }
    }

    private void getSongsToPlay() {
        Scanner input = new Scanner(System.in);  //Project requirement: Scanner class
        //Prompt user
        System.out.println("How many songs would you like to play?");
        int numSongs = input.nextInt();  //Project requirement: assignment operator "="

        //Check if valid number
        boolean tooFewSongs = numSongs < 0;
        boolean tooManySongs = numSongs > 5;
        boolean cantPlayThese = tooFewSongs || tooManySongs; //Project requirement: logical operator "OR"
        if (cantPlayThese)  //
        {
            System.err.println("Invalid number of songs.  Please enter between 1 and 5 songs");  //Project requirement: exception handling
            System.exit(-1);                                                                     //when invalid entry made
        }

        int numSongsSelected = 0;
        boolean done = false;
        do {                        //Project requirement: loop (using negative comparison)
            printSongList();
            System.out.println("Please choose a song: ");
            int nextSong = input.nextInt();
            boolean validSong = (nextSong >= 0) && (nextSong < songList.length);
            if (validSong) {
                this.songs.add(getSong(nextSong));
                numSongsSelected += 1;
            } else {
                System.out.println("Invalid choice, please choose again");
            }
            done = numSongsSelected >= numSongs;

        } while (!done);
    }

    //Create the list of songs for the user to choose.
    private void printSongList() {


        for (int i = 0; i < songList.length; i++){
            System.out.println ("[" + i + "] " + songList[i]);
        }
    }

    private void playSongs()
    {
        for (ChristmasSong song : songs)
        {
            System.out.println(song.playChristmasSong());
            waitToPlayNextSong();
        }
    }

    private static void waitToPlayNextSong()  //Project requirement: static method
    {
        try
        {
            // Delay 3 seconds before playing the next song
            long timeToSleep =  Math.abs(randomNumberGenerator.nextLong() % (3*SECONDS));//Project requirement: Math class method
            Thread.sleep(timeToSleep);
        }
        catch (InterruptedException e)
        {
            System.err.println("Who woke me up?");
        }
    }

    public static void main(String args[])
    {
        JukeBox jukeBox = new JukeBox();  //Project requirement: create an object

        jukeBox.getSongsToPlay();
        jukeBox.playSongs();
    }
}
