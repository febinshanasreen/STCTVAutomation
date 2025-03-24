# Data-Driven Automation Framework  

## Overview  
This automation framework is built using **Demo Web Shop** as the system under test. It follows a **data-driven approach** and is designed to support **parallel and sequential execution**. Additionally, it enables **cross-browser testing** across Chrome, Firefox, and Edge.  

## Tech Stack  
- **Build Tool:** Maven  
- **Automation Library:** Selenium WebDriver  
- **Test Framework:** TestNG  
- **Reporting:** Extent Reports  

## Features  
- Supports **parallel** and **sequential** execution  
- Cross-browser testing on **Chrome, Firefox, and Edge**  
- Data-driven test execution  
- Generates **Extent Reports** for test results  

## Project Structure  
```md
/src  
  ├── main/java                # Main framework utilities and configurations  
  ├── test/java                # Test cases and test data  
  ├── resources                # Config files (e.g., testng.xml, extent-config.xml)  
pom.xml                        # Maven dependencies and plugins  
README.md                      # Project documentation  
```

## Prerequisites  
Ensure you have the following installed:  
- **Java (JDK 11+)**  
- **Maven**  
- **Chrome, Firefox, and Edge browsers**  

## How to Run Tests  
To trigger automation tests, follow these steps:  

1. Open **Command Prompt/Terminal** at the project root level  
2. Run the following command:  
   ```sh
   mvn test
   ```  

## Test Reports  
- After execution, **Extent Reports** are generated in the `test-output` folder  
- Open the `index.html` file inside the `test-output/ExtentReports` directory to view the results  

## Contributing  
If you'd like to contribute to this project, feel free to fork the repository, make changes, and submit a pull request.  

---  
Let me know if you need additional details or modifications! 🚀

