package lightmanagement;

import java.util.Scanner;

public class Main {

    private static final StringBuilder MAIN_MENU = new StringBuilder(
            "******** MAIN MENU ************\n1) View state of Home\n2) Create a room \n3) Modify Room \n");
    private static final StringBuilder ROOM_MENU = new StringBuilder(
            "******** ROOM MENU ************\n1) View state of Room\n2) Modify Room Lighting\n3) Return to Main Menu\n");
    private static final StringBuilder LIGHT_MENU = new StringBuilder(
            "******** LIGHT MENU ************\n1) View state of Lights\n2) Modify Light \n3) Back to Main Menu\n");
    private static final StringBuilder UPDATE_LIGHT_MENU = new StringBuilder(
            "******** UPDATE LIGHT MENU ************\n1) Update Brightness\n2) Update LightMode\n3) Update LightState\n4) Back to Previous Menu\n");
    private static final StringBuilder UPDATE_LIGHT_STATE_MENU = new StringBuilder(
            "******** UPDATE LIGHT STATE MENU ************\n1) OFF\n2) ON\n3) Return Back\n");
    private static final StringBuilder UPDATE_LIGHT_MODE_MENU = new StringBuilder(
            "******** UPDATE LIGHT MODE MENU ************\n1) MORNING\n2) AFTERNOON\n3) EVENING\n4) RETURN\n");
    private static final StringBuilder BANNER = new StringBuilder("*******************************\n");

    public static void displayBannerHeader() {
        System.out.println(BANNER.toString());
    }

    public static void displayHomeBannerOptions() {
        displayBannerHeader();
        System.out.println(MAIN_MENU.toString());
        displayBannerHeader();
    }

    public static void displayRoomBannerOptions() {
        displayBannerHeader();
        System.out.println(ROOM_MENU.toString());
        displayBannerHeader();
    }

    public static void displayRoomCreateBannerOptions() {
        System.out.println("How Many Lights do you want in the Room? Value should be greater than 0\n");
    }

    public static void displayLightBannerOptions() {
        displayBannerHeader();
        System.out.println(LIGHT_MENU.toString());
        displayBannerHeader();
    }

    public static void displayLightUpdateBanner() {
        displayBannerHeader();
        System.out.println(UPDATE_LIGHT_MENU.toString());
        displayBannerHeader();
    }

    private static void displayLightStateBanner() {
        displayBannerHeader();
        System.out.println(UPDATE_LIGHT_STATE_MENU.toString());
        displayBannerHeader();
    }

    private static void displayLightModeBannerOptions() {
        displayBannerHeader();
        System.out.println(UPDATE_LIGHT_MODE_MENU.toString());

        displayBannerHeader();
    }

    public static int readInput(Scanner scanner) {
        int x = 0;
        try {
            x = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer option");
        }
        return x;
    }

    public static void main(String[] args) {

        Home home = new Home();
        try (Scanner scanner = new Scanner(System.in)) {
            int userInput = 0;
            System.out.println("Home Lighting ManageMent System");
            while (userInput != -1) {
                displayHomeBannerOptions();
                do {
                    userInput = readInput(scanner);
                } while (userInput == 0);

                try {
                    homeManagement(home, userInput, scanner);
                } catch (InvalidRoomCountException e) {
                    System.out.println(e.getMessage());
                }
            }

        }

    }

    static void homeManagement(Home home, int userInput, Scanner scanner) throws InvalidRoomCountException {
        switch (userInput) {
            case 1 -> System.out.println(home);
            case 2 -> home.addRoom(createRoom(scanner));
            case 3 -> {
                System.out.println("Which Room Do you want to update");
                int room = readInput(scanner);
                manageRoom(scanner, room, home);
            }
            default -> System.out.println("Invalid Option Selected");
        }

    }

    static void manageRoom(Scanner scanner, int roomId, Home home) throws InvalidRoomCountException {
        try {
            int roomModification = 0;
            Room room = home.getRooms().get(roomId - 1);

            if (home.getRoomCount() > 0) {

                while (roomModification != 3) {
                    displayRoomBannerOptions();
                    roomModification = readInput(scanner);
                    switch (roomModification) {
                        case 1 -> System.out.println(room);
                        case 2 -> {
                            System.out.println("Which Light do you want to update");
                            int lightId = readInput(scanner);
                            try {
                                updateRoomLight(room, lightId, scanner);
                            } catch (InvalidLightException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }

            } else
                throw new InvalidRoomCountException("Please create a room before proceeding");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Room does not exist. Please enter a room id from the below ");
            System.out.println(home);
        }

    }

    private static Room createRoom(Scanner scanner) {
        Room room = new Room();
        displayRoomCreateBannerOptions();
        int numberOfLights = -1;
        while (numberOfLights < 0) {
            numberOfLights = readInput(scanner);
        }

        for (int i = 0; i < numberOfLights; i++) {
            room.addLight(i + 1, new Light(LightMode.MORNING, LightState.OFF));
        }
        return room;
    }

    private static void updateRoomLight(Room room, int lightId, Scanner scanner) throws InvalidLightException {
        int lightModification = 0;
        if (room.getLights().containsKey(lightId)) {

            while (lightModification != 3) {
                displayLightBannerOptions();
                lightModification = readInput(scanner);
                Light light = room.getLight(lightId);
                switch (lightModification) {
                    case 1 -> System.out.println(room.getLights());
                    case 2 -> updateLight(light, scanner);
                    case 3 -> {
                    }
                }
            }

        } else {
            throw new InvalidLightException("Light Does not exist");
        }

    }

    private static void updateLight(Light light, Scanner scanner) {
        int lightModification = 0;
        while (lightModification != 4) {
            displayLightUpdateBanner();
            lightModification = readInput(scanner);
            switch (lightModification) {
                case 1:
                    updateLightBrightness(light, scanner);
                    break;
                case 2:
                    updateLightModeWorker(light, scanner);
                    break;
                case 3:
                    updateLightStateWorker(light, scanner);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Please input correct value from Menu");
            }

        }

    }

    private static void updateLightBrightness(Light light, Scanner scanner) {
        System.out.println("Enter Brightness");
        try {
            light.setLightBrightness(readInput(scanner));
        } catch (InvalidBrightness e) {
            System.out.println(e.getMessage());
            ;
        }

    }

    private static void updateLightModeWorker(Light light, Scanner scanner) {
        System.out.println("update lightmode");
        int lightMode = 0;
        boolean isModeUpdated = false;
        while (!isModeUpdated) {
            displayLightModeBannerOptions();
            lightMode = readInput(scanner);
            if (lightMode == 4)
                break;
            isModeUpdated = updateMode(lightMode, light);

        }
    }

    private static void updateLightStateWorker(Light light, Scanner scanner) {
        int lightState = 0;
        boolean isStateUpdated = false;
        while (!isStateUpdated) {
            displayLightStateBanner();
            lightState = readInput(scanner);
            if (lightState == 3)
                break;
            isStateUpdated = updateState(lightState, light);
        }
    }

    private static boolean updateState(int lightState, Light light) {
        boolean isUpdated = false;
        if (light != null) {
            switch (lightState) {
                case 1:
                    light.setLightState(LightState.OFF);
                    isUpdated = true;
                    break;
                case 2:
                    light.setLightState(LightState.ON);
                    isUpdated = true;
                    break;
                default:
                    System.out.println("Please Enter Correct Input ");
            }
        }
        return isUpdated;

    }

    private static boolean updateMode(int lightMode, Light light) {
        boolean isUpdated = false;
        switch (lightMode) {
            case 1:
                light.setLightMode(LightMode.MORNING);
                isUpdated = true;
                break;
            case 2:
                light.setLightMode(LightMode.AFTERNOON);
                isUpdated = true;
                break;
            case 3:
                light.setLightMode(LightMode.EVENING);
                isUpdated = true;
                break;
            default:
                System.out.println("Please Enter Correct Input ");
        }
        return isUpdated;
    }

}
