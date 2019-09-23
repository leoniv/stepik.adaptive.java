#!/bin/sh

JAVAC="javac -d ./bin -cp ./bin $* ./src/*"

exec $JAVAC
