package swingy.models;

import java.io.*;
import java.util.ArrayList;

public abstract class Model {
    protected String filename = null;
    private File _file = null;
    private FileReader _fileReader = null;
    private BufferedReader _bufferedReader = null;
    private BufferedWriter _bufferedWriter = null;
    private FileWriter _fileWriter = null;

    protected Model() {
    }

    protected ArrayList<String> readFile () {
        ArrayList<String> fileContent = new ArrayList<String> ();
        _file = new File(filename);
        try {
            String line;
            if (_file.exists () && !_file.isDirectory()) {
                _fileReader = new FileReader (_file);
                _bufferedReader = new BufferedReader (_fileReader);
                while ((line = _bufferedReader.readLine ()) != null) {
                    fileContent.add (line);
                }
            }
        } catch (IOException e) {
            System.err.println (e.getMessage());
        } finally {
            close(_bufferedReader, _fileReader);
        }
        return fileContent;
    }

    protected void writeFile (ArrayList<String> fileContent) {
        _file = new File(filename);
        try {
            _fileWriter = new FileWriter (filename);
            _bufferedWriter = new BufferedWriter (_fileWriter);
            for (String line : fileContent) {
                _bufferedWriter.write (line);
                _bufferedWriter.newLine ();
            }
        } catch (IOException e) {
            System.err.println (e.getMessage());
        } finally {
            close(_bufferedWriter, _fileWriter);
        }
    }

    private void close (Closeable a, Closeable b) {
        try {
            if (a != null) {
                a.close ();
            }
            if (b != null) {
                b.close ();
            }
        } catch (IOException e) {
            System.err.println (e.getMessage());
        }
    }
}
