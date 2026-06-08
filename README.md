# Java Swing Modular Calculator

A robust, desktop-based calculator built entirely in pure Java. Instead of relying on basic step-by-step logic, this application features a custom-built **Recursive Descent Parser** to accurately evaluate full mathematical expressions following proper BODMAS/PEMDAS order of operations.

## ✨ Features
* **Advanced Math Parsing:** Handles complex nested equations with parentheses (e.g., `(5 + 5.5) * 2`).
* **Clean Architecture:** Strictly separates the graphical user interface (View) from the mathematical logic (Model).
* **Smart Formatting:** Eliminates floating-point visual errors while preserving precision.
* **Running History:** Keeps a scrollable log of all past calculations during the session.
* **Dark Mode:** Built-in UI toggle to switch the interface between light and dark themes.
* **Graceful Error Handling:** Catches division by zero and syntax errors without crashing the application.

## 🗂️ File Structure
* `CalculatorGUI.java`: The front-end view. Manages the Swing components, grid layout, button event listeners, and color theme rendering.
* `CalculatorEngine.java`: The back-end model. Processes string equations character-by-character to evaluate the math independently of the UI.

## 🚀 How to Run

### Prerequisites
* [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) installed on your machine.

### Execution via Terminal/Command Prompt
1. Clone the repository:
   ```bash
   git clone https://github.com/Florax5663/Java-Swing-Calculator.git

2. Navigate into the project directory:
   ```bash
   cd Java-Swing-Calculator


3. Compile all the source code:
   ```bash
   javac *.java


4. Run the graphical interface:
   ```bash
   java CalculatorGUI


## 👨‍💻 Author

**Piuse Samanta** Chaitanya Bharathi Institute of Technology