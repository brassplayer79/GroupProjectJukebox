public class TwelveDays extends ChristmasSong  //Project requirement: inheritance, extends
{

    public TwelveDays()  //Project requirement: constructor
    {
        super();  //Project requirement: super keyword
    }
    public String playChristmasSong()
    {
        String verses[] = {                      //Project requirement: array
                "A partridge in a pear tree",
                "Two turtle doves",
                "Three french hens",
                "Four calling birds",
                "Five Golden Rings",
                "Six geese a-laying",
                "Seven swans a-swimming",
                "Eight maids a-milking",
                "Nine ladies dancing",
                "Ten lords a-leaping",
                "Eleven pipers piping",
                "Twelve drummers drumming"
        };
        String song = "";
        for (int verse = 0; verse < 12; verse = verse + 1)
        {
            song = song.concat(getChorus(verse) + "\n" + verses[verse] + "\n");  //Project requirement: String class method
        }

        return song;
    }

    private String getChorus(int verseNumber) //Project requirement: method passing arg by value
    {
        verseNumber = verseNumber + 1; //Passed by value and not by reference
        String chorus = "On the ";
        switch (verseNumber)
        {
            case 1:
                chorus += verseNumber + "st ";
                break;

            case 2:
                chorus += verseNumber + "nd ";
                break;

            case 3:
                chorus += verseNumber + "rd ";
                break;

            default:
                chorus += verseNumber + "th ";
        }
        chorus += "day of Christmas my true love gave to me";

        return chorus;  //Project requirement: return value from method
    }
}