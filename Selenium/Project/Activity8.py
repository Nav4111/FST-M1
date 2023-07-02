from selenium import webdriver
from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.firefox.service import Service as FirefoxService
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait
from webdriver_manager.firefox import GeckoDriverManager

# Set up the Firefox Driver with WebDriverManger
service = FirefoxService(GeckoDriverManager().install())

# Start the Driver
with webdriver.Firefox(service=service) as driver:
    # Initialize wait object
    wait = WebDriverWait(driver, 10)
    actions = ActionChains(driver)

    # Navigate to the URL
    driver.get("http://alchemy.hguy.co/crm")
    print("Page title is: ", driver.title)

    # Find the username and password fields
    username = driver.find_element(By.ID, "user_name")
    password = driver.find_element(By.ID, "username_password")

    # Enter credentials and login
    username.send_keys("admin")
    password.send_keys("pa$$w0rd")
    driver.find_element(By.ID, "bigbutton").click()

    # navigates to sales->accounts page
    sales_tab = driver.find_element(By.ID, "grouptab_0")
    actions.move_to_element(sales_tab).perform()
    driver.find_element(By.XPATH,
                        "//*[@id='grouptab_0']/following-sibling::ul/li/a[@id='moduleTab_9_Accounts']").click()

    # wait for the table to load
    wait.until(
        expected_conditions.visibility_of_element_located((By.XPATH, "//*[@class='list view table-responsive']")))

    oddRows = driver.find_elements(By.XPATH,
        "//table[contains(@class,'list view table-responsive')]/tbody/tr[contains(@class,'oddListRowS1')]")
    print("Number of odd rows: ", len(oddRows))

    print("The names of first 5 odd-numbered ordered accounts: ")
    for i in range(0, 5):
        print("Row Number: ",i+1)
        print(oddRows[i].text)

