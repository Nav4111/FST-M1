import pytest


@pytest.fixture(scope="module")
def input_value():
    input = 39
    return input


# Create fixture
@pytest.fixture
def num_list():

    # Create list
    list = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    # Alternatively
    # list = list(range(11))

    return list