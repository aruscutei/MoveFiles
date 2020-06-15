import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MoveFiles {

    public static void main(String[] args) throws IOException {

        Logger logger = Logger.getLogger(MoveFiles.class.getName());
        FileHandler fh = null;
        SimpleDateFormat formatData = new SimpleDateFormat("M-d_HHmmss");
        try {
            fh = new FileHandler("C:\\temp\\MyLogFile"
                    + formatData.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);

        logger.info("Information");

        String[] pathNames;
        File f = new File("C:\\source");
        pathNames = f.list();

        for (int i = 0; i < pathNames.length; i++) {
            if (!pathNames[i].contains("-")) {
                logger.severe("The name of the file " + pathNames[i] + " does not have the correct format");
                logger.warning("Due to the wrong format of the file above, not all files will be moved");
            }
        }



        try {
            for (int i = 0; i < pathNames.length; i++) {
                verifyMakeDirectory("C:\\source", "C:\\destination\\" + pathNames[i].substring(0, pathNames[i].indexOf("-")),
                        "C:destination");

                File file = new File("C:\\source\\" + pathNames[i]);

                // renaming the file and moving it to a new location
                if (file.renameTo
                        (new File("C:\\destination\\" + pathNames[i].substring(0, pathNames[i].indexOf("-"))
                                + "\\" + pathNames[i]))) {


                    logger.info("File " + pathNames[i] + " moved successfully in the location C:\\destination\\" + pathNames[i].substring(0, pathNames[i].indexOf("-"))
                            + "\\" + pathNames[i]);
                } else {
                    logger.severe("Failed to move the file " + pathNames[i] + " in the location C:\\\\destination\\\\\" +pathNames[i].substring(0, pathNames[i].indexOf(\"-\"))\n" +
                            "                    +\"-\"+ pathNames[i]");
                }

            }


        } catch (StringIndexOutOfBoundsException e) {
            logger.severe("String index was out of bounds. The file with incorrect format and after this one failed to move");
        }


    }


        public static void verifyMakeDirectory(String origin, String destDir, String destination) throws IOException {

            Path FROM = Paths.get(origin);
            Path TO = Paths.get(destination);
            File directory = new File(destDir);

            if (!directory.exists()) {
                directory.mkdir();
            }


        }

    }





