import java.util.Scanner;

public class JavaCalc {

    private double lastResult;
    private int totalCalculations;
    private boolean isRunning;
    private Scanner inputReader;

    public JavaCalc() {
        this.lastResult = 0.0;
        this.totalCalculations = 0;
        this.isRunning = true;
        this.inputReader = new Scanner(System.in);
    }

    public double performAddition(double firstValue, double secondValue) {
        double outcome = firstValue + secondValue;
        this.lastResult = outcome;
        this.totalCalculations++;
        return outcome;
    }

    public double performSubtraction(double firstValue, double secondValue) {
        double outcome = firstValue - secondValue;
        this.lastResult = outcome;
        this.totalCalculations++;
        return outcome;
    }

    public double performMultiplication(double firstValue, double secondValue) {
        double outcome = firstValue * secondValue;
        this.lastResult = outcome;
        this.totalCalculations++;
        return outcome;
    }

    public double performDivision(double firstValue, double secondValue) {
        if (secondValue == 0) {
            System.out.println(">> Warning: Cannot divide by zero!");
            return Double.NaN;
        }
        double outcome = firstValue / secondValue;
        this.lastResult = outcome;
        this.totalCalculations++;
        return outcome;
    }

    public double performModulus(double firstValue, double secondValue) {
        if (secondValue == 0) {
            System.out.println(">> Warning: Cannot perform modulus with zero!");
            return Double.NaN;
        }
        double outcome = firstValue % secondValue;
        this.lastResult = outcome;
        this.totalCalculations++;
        return outcome;
    }

    public double performPower(double baseValue, double exponentValue) {
        double outcome = 1.0;
        int exponent = (int) exponentValue;
        
        if (exponent >= 0) {
            for (int counter = 0; counter < exponent; counter++) {
                outcome = outcome * baseValue;
            }
        } else {
            for (int counter = 0; counter < Math.abs(exponent); counter++) {
                outcome = outcome * baseValue;
            }
            outcome = 1.0 / outcome;
        }
        
        this.lastResult = outcome;
        this.totalCalculations++;
        return outcome;
    }

    public double calculateSquareRoot(double inputValue) {
        if (inputValue < 0) {
            System.out.println(">> Warning: Cannot calculate square root of negative number!");
            return Double.NaN;
        }
        
        if (inputValue == 0) {
            this.lastResult = 0;
            this.totalCalculations++;
            return 0;
        }

        double estimation = inputValue / 2.0;
        double tolerance = 0.00001;
        
        while (true) {
            double betterEstimation = (estimation + inputValue / estimation) / 2.0;
            double difference = estimation - betterEstimation;
            
            if (difference < 0) {
                difference = -difference;
            }
            
            if (difference < tolerance) {
                break;
            }
            estimation = betterEstimation;
        }
        
        this.lastResult = estimation;
        this.totalCalculations++;
        return estimation;
    }

    public void displayWelcomeMessage() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║          WELCOME TO JAVACALC               ║");
        System.out.println("║       Your Friendly Math Companion         ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println();
    }

    public void displayMenuOptions() {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│            AVAILABLE OPERATIONS            │");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.println("│  [1] Addition        (+)                   │");
        System.out.println("│  [2] Subtraction     (-)                   │");
        System.out.println("│  [3] Multiplication  (*)                   │");
        System.out.println("│  [4] Division        (/)                   │");
        System.out.println("│  [5] Modulus         (%)                   │");
        System.out.println("│  [6] Power           (^)                   │");
        System.out.println("│  [7] Square Root                           │");
        System.out.println("│  [8] Use Last Result                       │");
        System.out.println("│  [9] View Statistics                       │");
        System.out.println("│  [0] Exit Calculator                       │");
        System.out.println("└────────────────────────────────────────────┘");
    }

    public int getUserChoice() {
        System.out.print(">> Enter your choice: ");
        
        while (!inputReader.hasNextInt()) {
            System.out.println(">> Please enter a valid number!");
            inputReader.next();
            System.out.print(">> Enter your choice: ");
        }
        
        return inputReader.nextInt();
    }

    public double getNumberFromUser(String promptMessage) {
        System.out.print(promptMessage);
        
        while (!inputReader.hasNextDouble()) {
            System.out.println(">> Please enter a valid number!");
            inputReader.next();
            System.out.print(promptMessage);
        }
        
        return inputReader.nextDouble();
    }

    public void displayResult(String operationName, double result) {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│              CALCULATION RESULT            │");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.printf("│  Operation: %-30s │%n", operationName);
        System.out.printf("│  Result:    %-30.6f │%n", result);
        System.out.println("└────────────────────────────────────────────┘");
    }

    public void displayStatistics() {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│            SESSION STATISTICS              │");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.printf("│  Total Calculations: %-21d │%n", this.totalCalculations);
        System.out.printf("│  Last Result:        %-21.6f │%n", this.lastResult);
        System.out.println("└────────────────────────────────────────────┘");
    }

    public void displayGoodbyeMessage() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║       THANK YOU FOR USING JAVACALC!        ║");
        System.out.printf("║      You performed %3d calculations         ║%n", this.totalCalculations);
        System.out.println("║            See you next time!              ║");
        System.out.println("╚════════════════════════════════════════════╝");
    }

    public void processTwoNumberOperation(int operationType) {
        double firstNumber = getNumberFromUser(">> Enter first number: ");
        double secondNumber = getNumberFromUser(">> Enter second number: ");
        double result = 0.0;
        String operationLabel = "";

        switch (operationType) {
            case 1:
                result = performAddition(firstNumber, secondNumber);
                operationLabel = firstNumber + " + " + secondNumber;
                break;
            case 2:
                result = performSubtraction(firstNumber, secondNumber);
                operationLabel = firstNumber + " - " + secondNumber;
                break;
            case 3:
                result = performMultiplication(firstNumber, secondNumber);
                operationLabel = firstNumber + " * " + secondNumber;
                break;
            case 4:
                result = performDivision(firstNumber, secondNumber);
                operationLabel = firstNumber + " / " + secondNumber;
                break;
            case 5:
                result = performModulus(firstNumber, secondNumber);
                operationLabel = firstNumber + " % " + secondNumber;
                break;
            case 6:
                result = performPower(firstNumber, secondNumber);
                operationLabel = firstNumber + " ^ " + (int) secondNumber;
                break;
        }

        if (!Double.isNaN(result)) {
            displayResult(operationLabel, result);
        }
    }

    public void processSquareRootOperation() {
        double inputNumber = getNumberFromUser(">> Enter number for square root: ");
        double result = calculateSquareRoot(inputNumber);
        
        if (!Double.isNaN(result)) {
            String operationLabel = "√" + inputNumber;
            displayResult(operationLabel, result);
        }
    }

    public void processUseLastResult() {
        if (this.totalCalculations == 0) {
            System.out.println(">> No previous calculation available!");
            return;
        }

        System.out.println(">> Last result is: " + this.lastResult);
        System.out.println(">> Choose operation to perform with this result:");
        System.out.println("   [1] Add  [2] Subtract  [3] Multiply  [4] Divide");
        
        int operation = getUserChoice();
        
        if (operation < 1 || operation > 4) {
            System.out.println(">> Invalid operation selected!");
            return;
        }

        double secondNumber = getNumberFromUser(">> Enter second number: ");
        double result = 0.0;
        String operationLabel = "";

        switch (operation) {
            case 1:
                result = performAddition(this.lastResult, secondNumber);
                operationLabel = this.lastResult + " + " + secondNumber;
                break;
            case 2:
                result = performSubtraction(this.lastResult, secondNumber);
                operationLabel = this.lastResult + " - " + secondNumber;
                break;
            case 3:
                result = performMultiplication(this.lastResult, secondNumber);
                operationLabel = this.lastResult + " * " + secondNumber;
                break;
            case 4:
                result = performDivision(this.lastResult, secondNumber);
                operationLabel = this.lastResult + " / " + secondNumber;
                break;
        }

        if (!Double.isNaN(result)) {
            displayResult(operationLabel, result);
        }
    }

    public void runCalculator() {
        displayWelcomeMessage();

        while (this.isRunning) {
            displayMenuOptions();
            int userChoice = getUserChoice();

            switch (userChoice) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    processTwoNumberOperation(userChoice);
                    break;
                case 7:
                    processSquareRootOperation();
                    break;
                case 8:
                    processUseLastResult();
                    break;
                case 9:
                    displayStatistics();
                    break;
                case 0:
                    this.isRunning = false;
                    break;
                default:
                    System.out.println(">> Invalid choice! Please select 0-9.");
            }
        }

        displayGoodbyeMessage();
        inputReader.close();
    }

    public static void main(String[] args) {
        JavaCalc calculator = new JavaCalc();
        calculator.runCalculator();
    }
}