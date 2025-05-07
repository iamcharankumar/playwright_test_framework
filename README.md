![Playwright](https://img.shields.io/badge/-playwright-%232EAD33?style=for-the-badge&logo=playwright&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)
![Maven](https://img.shields.io/badge/apachemaven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)
![CodeCov](https://img.shields.io/badge/codecov-%23ff0077.svg?style=for-the-badge&logo=codecov&logoColor=white)
![Discord](https://img.shields.io/badge/Discord-%235865F2.svg?style=for-the-badge&logo=discord&logoColor=white)

[![UI test execution](https://github.com/iamcharankumar/playwright_test_framework/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/iamcharankumar/playwright_test_framework/actions/workflows/ci.yml)
[![CodeQL](https://github.com/iamcharankumar/playwright_test_framework/actions/workflows/codeql.yml/badge.svg)](https://github.com/iamcharankumar/playwright_test_framework/actions/workflows/codeql.yml)
[![codecov](https://codecov.io/gh/iamcharankumar/playwright_test_framework/branch/master/graph/badge.svg?token=HT5GS0RC0C)](https://codecov.io/gh/iamcharankumar/playwright_test_framework)

# ABOUT

Automated end-to-end testing framework built with Playwright and Java, tailored for any website. Designed
for scalability and maintainability, this framework covers critical test scenarios with robust assertions and clean,
modular code. Perfect for those looking to speed up their test automation journey or enhance their Playwright skills!

# STEPS FOR THE TEST EXECUTION IN LOCAL

1. `git clone https://github.com/iamcharankumar/playwright_test_framework.git`
2. `cd playwright_test_framework`
3. `git pull`
4. `mvn clean test -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`

**SUPPORTED BROWSERS**

- Chrome `-Dbrowser=chrome` (default value is `chrome`)
- Firefox `-Drunmode=firefox`
- Microsoft Edge `-Drunmode=msedge`

**SUPPORTED RUN MODES**

- local `-Drunmode=local` (default value is `local`)
- headless `-Drunmode=headless`

# BROWSER CREATION FLOW

- A client requests a browser page from `BrowserManager.getBrowserPage()`
- `BrowserManager` reads system properties to determine browser type and headless mode
- `BrowserFactory` creates the appropriate browser implementation based on the browser name
- The browser implementation creates a session with the required configuration
- `BrowserManager` creates and returns a new page from the browser context
- When finished, the client calls `destroyBrowserPage()` to clean up resources.

<img width="1697" alt="Browser_Creation_Flow" src="https://github.com/user-attachments/assets/34f8530d-33a7-4612-9346-2fc5958f41da" />


# CHROME DEVTOOLS PROTOCOL IMPLEMENTATION EXPLAINED

**1. Initialize the CDP Session**

#### What Happens?

    - You (the test) ask CdpUtils to start monitoring.
    - CdpUtils tells CDPSessionHandlerImpl to set things up.
    - Playwright provides the tools (CDPSession).

![Initialize_the_CDP_Session](https://github.com/user-attachments/assets/b5016da0-1126-4ca4-ad3f-5dd11ff14779)

**2. Listen for Network Errors**

#### What Happens?

    - You ask to log errors (e.g., HTTP 404/500).
    - The CDPSessionHandlerImpl sets up a listener in the browser.

![Listen_for_Network_Errors](https://github.com/user-attachments/assets/9406c6cd-d4e5-4cfd-b2b8-789922dbc4e1)

**3. Browser Fires an Event**

#### What Happens?

    - The browser detects a failed request → notifies CDPSessionHandlerImpl.
    - The error details are passed back to your test (e.g., to log or retry).

![ Browser_Fires_an_Event](https://github.com/user-attachments/assets/65193194-ffa1-4775-92f9-52ed8be1f34f)

# REPORTPORTAL INTEGRATION

- To integrate your test reports with the open source tool - [**Reportportal**](https://reportportal.io/docs/), please
  refer to
  this [section](https://github.com/iamcharankumar/web_test_framework?tab=readme-ov-file#steps-for-integrating-testng--report-portal)
  in my java-selenium framework repo for installation as well as the integration.

# INTEGRATE DISCORD MESSAGE SERVICE

- To send test reports from Reportportal to any Discord Message Channel, please refer to
  this [section](https://github.com/iamcharankumar/web_test_framework?tab=readme-ov-file#steps-for-integrating-test-reports-report-portal-url-with-discord-message-service)
  and all the classes in
  this [package](https://github.com/iamcharankumar/web_test_framework/tree/master/src/main/java/io/saucelabs/portal/qa/services/discord)
  of the same java-selenium framework repo.
- In this framework, Discord has been integrated via Github actions. Refer final result in the image below.
  <img width="1657" alt="Discord_Report_Message" src="https://github.com/user-attachments/assets/0f808ece-bcb3-486a-9e70-620dcb7b0f95">

# CODE COVERAGE [CODECOV TOOL]

- The code coverage is leveraged via Github Actions integrating codecov tool.
- The latest code coverage for this project is
  available [here](https://app.codecov.io/gh/iamcharankumar/playwright_test_framework).
- The code coverage grid:
  ![codecov](https://codecov.io/gh/iamcharankumar/playwright_test_framework/graphs/tree.svg?token=HT5GS0RC0C)

# EXTRAS

- Below is the maven commands combo that will help you to cover all the supported browsers and runmodes.
- These maven commands (no testng.xml required) are executed with the respective groups and thread counts.
  The listeners [screenshot, retry, etc] are configured in "pom.xml" under "< property >" tag.

#### BROWSERS & RUN MODES

| Sl.No | Browser Name | Run Mode | mvn command                                                                                                                                               |
|-------|--------------|----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Chrome       | Local    | `mvn clean test -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`                                      |
| 2     | Chrome       | Headless | `mvn clean test -Drunmode=headless -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`                   |
| 3     | Firefox      | Local    | `mvn clean test -Dbrowser=firefox -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`                    |
| 4     | Firefox      | Headless | `mvn clean test -Drunmode=headless -Dbrowser=firefox -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3` |
| 5     | Edge         | Local    | `mvn clean test -Dbrowser=msedge -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`                     |
| 6     | Edge         | Headless | `mvn clean test -Drunmode=headless -Dbrowser=msedge -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`  |

**NOTE**: These above commands (no testng.xml required) will run the tests in parallel with the specified thread count
and with the respective groups and thread counts.
The screenshot listeners are configured in "pom.xml" under "< property >" tag.

#### DECLUTTERING MAVEN OUTPUT

- Maven usually floods the console with logs during test execution, making it hard to spot what's important.
  A clean, minimal, Node.js-style output for a Java project felt impossible—until I
  found this [maven dependency](https://mvnrepository.com/artifact/me.fabriciorby/maven-surefire-junit5-tree-reporter).
- Its purpose is simple: **"What happened to my test cases?"** That’s exactly what it shows—straight to the point, no
  clutter.
- By following this [post](https://medium.com/wearewaes/my-journey-to-a-clear-test-output-in-maven-df82fe272249)
  by [Fabricio](https://github.com/fabriciorby),
  I was able to configure it easily and get the clean output shown below.
- The output works locally and in GitHub Actions as well.
- Huge respect and thanks to the author for this
  brilliant [work](https://github.com/fabriciorby/maven-surefire-junit5-tree-reporter?tab=readme-ov-file)!
  ❤️

**Before the Decluttering**

<img width="921" alt="Before" src="https://github.com/user-attachments/assets/6478675f-bc73-4ae8-8d0f-6a45a30cf7b1" />

**After the Decluttering**

<img width="1728" alt="After" src="https://github.com/user-attachments/assets/66dee2f2-87be-4f46-a6f5-011967f6a9ff" />

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=iamcharankumar/playwright_test_framework&type=Date)](https://star-history.com/#iamcharankumar/playwright_test_framework&Date)
