#!/bin/sh

JAVAC="javac -d ./bin -cp ./bin -g $* ./src/org/hello/java/*.java"

exec $JAVAC
