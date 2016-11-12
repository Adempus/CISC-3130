cmake_minimum_required(VERSION 3.6)
project(Homework4)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp Item.h ShoppingCart.h Shopper.h HardwareStore.h Inventory.h ShoppingCart.cpp Shopper.cpp HardwareStore.cpp)
add_executable(Homework4 ${SOURCE_FILES})