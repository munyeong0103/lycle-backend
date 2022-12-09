#!/usr/bin/env bash

REPOSITORY=/lycle-backend
cd $REPOSITORY

APP_NAME=lycle-backend-0.0.1-SNAPSHOT.jar

CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 종료할것 없음."
else
  echo "> kill -9 $CURRENT_PID"
  kill $CURRENT_PID
  sleep 5
fi

PROJECT_ROOT="/home/ubuntu/lycle-backend/build/libs"
JAR_FILE="$PROJECT_ROOT/lycle-backend-0.0.1-SNAPSHOT.jar"

# jar 파일 실행
sleep 15
echo "$TIME_NOW > $JAR_FILE 파일 실행"
nohup java -jar $JAR_FILE &