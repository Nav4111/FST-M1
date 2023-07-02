from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.firefox.service import Service as FirefoxService
from selenium.webdriver.support.wait import WebDriverWait
from webdriver_manager.firefox import GeckoDriverManager

# Set up the Firefox Driver with WebDriverManger
service = FirefoxService(GeckoDriverManager().install())

# Start the Driver
with webdriver.Firefox(service=service) as driver:
    # Initialize wait object
    wait = WebDriverWait(driver, 10)

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

    navBar = driver.find_element(By.XPATH, "//*[@class='navbar navbar-inverse navbar-fixed-top']")
    print("Color of the navigation menu: ", navBar.value_of_css_property("color"))

