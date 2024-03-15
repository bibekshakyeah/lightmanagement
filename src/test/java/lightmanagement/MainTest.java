package lightmanagement;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    void testReadInput() {
        String input = "5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertEquals(5, Main.readInput(scanner));
    }

    @Test
    void testReadInputInvalid() {
        String input = "acv\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertEquals(0, Main.readInput(scanner));

    }

    @Test
    void testDisplayHome() {
        Home home = new Home();
        assertEquals("Home [rooms=[]]", home.toString());
    }

    @Test
    void testDisplayHomeBannerOptions() {
        Main.displayHomeBannerOptions();
        String capturedOutput = outputStreamCaptor.toString();

        assertTrue(capturedOutput.contains("******** MAIN MENU ************"));
        assertTrue(capturedOutput.contains("1) View state of Home"));
        assertTrue(capturedOutput.contains("2) Create a room"));
        assertTrue(capturedOutput.contains("3) Modify Room"));
    }


    @Test
    void testDisplayRoomBannerOptions() {
        Main.displayRoomBannerOptions();
        String capturedOutput = outputStreamCaptor.toString();

        assertTrue(capturedOutput.contains("1) View state of Room"));
        assertTrue(capturedOutput.contains("2) Modify Room Lighting"));
        assertTrue(capturedOutput.contains("3) Return to Main Menu"));
    }


    @Test
    void testDisplayRoomCreateBannerOptions() {
        Main.displayRoomCreateBannerOptions();
        assertTrue(outputStreamCaptor.toString().contains("How Many Lights do you want in the Room? Value should be greater than 0"));
    }

    @Test
    void testDisplayLightBannerOptions() {
        Main.displayLightBannerOptions();
        String capturedOutput = outputStreamCaptor.toString();

        assertTrue(capturedOutput.contains("View state of Lights"));
        assertTrue(capturedOutput.contains("Modify Light"));
        assertTrue(capturedOutput.contains("Back to Main Menu"));
    }

    @Test
    void testDisplayLightUpdateBanner() {
        Main.displayLightUpdateBanner();
        String capturedOutput = outputStreamCaptor.toString();

        assertTrue(capturedOutput.contains("1) Update Brightness"));
        assertTrue(capturedOutput.contains("2) Update LightMode"));
        assertTrue(capturedOutput.contains("3) Update LightState"));
        assertTrue(capturedOutput.contains("4) Back to Previous Menu"));
    }
    @Test
    void testHomeManagement_ViewStateOfHome() throws InvalidRoomCountException {
        Home home = new Home();
        Scanner scanner = new Scanner(new ByteArrayInputStream("-1".getBytes()));
        Main.homeManagement(home, 1, scanner);
        assertEquals("Home [rooms=[]]", outputStreamCaptor.toString().trim());
    }

    @Test
    void testHomeManagement_CreateRoom() throws InvalidRoomCountException {
        Home home = new Home();
        ByteArrayInputStream in = new ByteArrayInputStream("2\n1\n-1".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        Main.homeManagement(home, 2, scanner);
        assertEquals("How Many Lights do you want in the Room? Value should be greater than 0", outputStreamCaptor.toString().trim());
    }

    @Test
    void testHomeManagement_InvalidOption() throws InvalidRoomCountException {
        Home home = new Home();
        Scanner scanner = new Scanner(new ByteArrayInputStream("5\n-1".getBytes()));
        Main.homeManagement(home, 5, scanner);
        assertEquals("Invalid Option Selected", outputStreamCaptor.toString().trim());
    }

    @Test
    void testMain() {
        // Define input and expected output
        String input = "1\n-1\n"; // Simulate selecting option 1 to view home state and then exiting
        String expectedOutput = "Home Lighting ManageMent System\n" +
                "*******************************\n" +
                "\n" +
                "******** MAIN MENU ************\n" +
                "1) View state of Home\n" +
                "2) Create a room \n" +
                "3) Modify Room \n" +
                "\n" +
                "*******************************\n" +
                "\n" +
                "Home [rooms=[]]\n" +
                "*******************************\n" +
                "\n" +
                "******** MAIN MENU ************\n" +
                "1) View state of Home\n" +
                "2) Create a room \n" +
                "3) Modify Room \n" +
                "\n" +
                "*******************************\n" +
                "\n" +
                "Invalid Option Selected\n";

        // Redirect standard input and output
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            // Call the main method
            Main.main(null);

            // Verify the output
            assertEquals(expectedOutput, outputStream.toString());
        } finally {
            // Reset standard input and output
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }


    @Test
    void testManageRoom() {
        // Define input and expected output
        String input = "1\n3\n"; // Simulate selecting option 1 to view room state and then exiting
        String expectedOutput = "*******************************\n" +
                "\n" +
                "******** ROOM MENU ************\n" +
                "1) View state of Room\n" +
                "2) Modify Room Lighting\n" +
                "3) Return to Main Menu\n" +
                "\n" +
                "*******************************\n" +
                "\n" +
                "Room [roomID=1, numberOfLights=0, lights={}]\n" +
                "*******************************\n" +
                "\n" +
                "******** ROOM MENU ************\n" +
                "1) View state of Room\n" +
                "2) Modify Room Lighting\n" +
                "3) Return to Main Menu\n" +
                "\n" +
                "*******************************\n" +
                "\n";

        // Create a sample home with a room
        Home home = new Home();
        Room room = new Room();
        home.addRoom(room);

        // Redirect standard input and output
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            // Call the manageRoom method
            Main.manageRoom(new Scanner(System.in), 1, home);

            // Verify the output
            assertEquals(expectedOutput, outputStream.toString());
        } catch (InvalidRoomCountException e) {
            e.printStackTrace();
        } finally {
            // Reset standard input and output
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    @Test
    void testHomeManagement() throws InvalidRoomCountException {
        // Define input and expected output for each case
        String input1 = "1\n"; // Simulate selecting option 1
        String input2 = "2\n"; // Simulate selecting option 2
        String input3 = "3\n1\n3\n"; // Simulate selecting option 3, then room ID 1, then exit
        String input4 = "4\n"; // Simulate selecting an invalid option
        String expectedOutput1 = "Home [rooms=[]]\n"; // Expected output for option 1
        String expectedOutput2 = "How Many Lights do you want in the Room? Value should be greater than 0\n\n";
        String expectedOutput3 = "Which Room Do you want to update\n" +
                "Room does not exist. Please enter a room id from the below \n" +
                "Home [rooms=[Room [roomID=1, numberOfLights=2, lights={1=Light [lightMode=MORNING, lightState=OFF, lightBrightness=0], 2=Light [lightMode=MORNING, lightState=OFF, lightBrightness=0]}]]]\n"; // Expected output for option 3
        String expectedOutput4 = "Invalid Option Selected\n"; // Expected output for an invalid option

        // Create a sample home
        Home home = new Home();

        // Redirect standard input and output
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            // Case 1: Selecting option 1
            ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream1));
            System.setIn(new ByteArrayInputStream(input1.getBytes()));
            Main.homeManagement(home, 1, new Scanner(System.in));
            assertEquals(expectedOutput1, outputStream1.toString());

            // Case 2: Selecting option 2
            ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream2));
            System.setIn(new ByteArrayInputStream(input2.getBytes()));
            Main.homeManagement(home, 2, new Scanner(System.in));
            assertEquals(expectedOutput2, outputStream2.toString());

            // Case 3: Selecting option 3
            ByteArrayOutputStream outputStream3 = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream3));
            System.setIn(new ByteArrayInputStream(input3.getBytes()));
            Main.homeManagement(home, 3, new Scanner(System.in));
            assertEquals(expectedOutput3, outputStream3.toString());

            // Case 4: Selecting an invalid option
            ByteArrayOutputStream outputStream4 = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream4));
            System.setIn(new ByteArrayInputStream(input4.getBytes()));
            Main.homeManagement(home, 4, new Scanner(System.in));
            assertEquals(expectedOutput4, outputStream4.toString());
        } finally {
            // Reset standard input and output
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    @Test
    void testManageRoomForCase2() throws InvalidRoomCountException {
        // Define input and expected output for each case
        String input1 = "1\n"; // Simulate selecting option 1
        String input2 = "2\n1\n3\n"; // Simulate selecting option 2, then light ID 1, then exit
        String input3 = "3\n"; // Simulate selecting an invalid option
        String expectedOutput1 = ""; // Expected output for option 1
        String expectedOutput2 = "*******************************\n" +
                "\n" +
                "******** ROOM MENU ************\n" +
                "1) View state of Room\n" +
                "2) Modify Room Lighting\n" +
                "3) Return to Main Menu\n" +
                "\n" +
                "*******************************\n" +
                "\n" +
                "Which Light do you want to update\n" +
                "Light Does not exist\n" +
                "*******************************\n" +
                "\n" +
                "******** ROOM MENU ************\n" +
                "1) View state of Room\n" +
                "2) Modify Room Lighting\n" +
                "3) Return to Main Menu\n" +
                "\n" +
                "*******************************\n" +
                "\n"; // Expected output for option 2
        String expectedOutput3 = "*******************************\n" +
                "\n" +
                "******** ROOM MENU ************\n" +
                "1) View state of Room\n" +
                "2) Modify Room Lighting\n" +
                "3) Return to Main Menu\n" +
                "\n" +
                "*******************************\n" +
                "\n"; // Expected output for an invalid option

        // Create a sample home and room
        Home home = new Home();
        Room room = new Room();
        home.addRoom(room);

        // Redirect standard input and output
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            // Case 1: Selecting option 1
            ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream1));
            System.setIn(new ByteArrayInputStream(input1.getBytes()));
            assertThrows(NoSuchElementException.class,()->Main.manageRoom(new Scanner(System.in), 1, home));
            // Case 2: Selecting option 2
            ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream2));
            System.setIn(new ByteArrayInputStream(input2.getBytes()));
            Main.manageRoom(new Scanner(System.in), 1, home);
            assertEquals(expectedOutput2, outputStream2.toString());

            // Case 3: Selecting an invalid option
            ByteArrayOutputStream outputStream3 = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream3));
            System.setIn(new ByteArrayInputStream(input3.getBytes()));
            Main.manageRoom(new Scanner(System.in), 1, home);
            assertEquals(expectedOutput3, outputStream3.toString());
        } finally {
            // Reset standard input and output
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}

