#!/bin/sh

JAVAC="javac -d ./build -cp ./build -g $* ./src/org/hello/java/*.java"

exec $JAVAC
