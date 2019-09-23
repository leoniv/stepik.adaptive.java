#!/bin/sh

JAVAC="javac -d ./bin -cp ./bin $* ./src/org/hello/java/*.java"

exec $JAVAC
