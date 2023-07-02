import time

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

    # select and click on Sales->lead option
    sales_tab = driver.find_element(By.ID, "grouptab_0")
    actions.move_to_element(sales_tab).perform()
    driver.find_element(By.XPATH,
                        "//*[@id='grouptab_0']/following-sibling::ul/li/a[@id='moduleTab_9_Leads']").click()

    # find and click on additional details icon
    time.sleep(2)
    wait.until(expected_conditions.visibility_of_element_located((By.XPATH, "//tr[1]/td/span/span[@title='Additional Details']"))).click()
    time.sleep(1)

    # get phone number from pop up
    phoneNum = driver.find_element(By.XPATH, "//*[@id='ui-id-5']/span[@class='phone']").text
    print("Phone Number of selected lead: ", phoneNum)
