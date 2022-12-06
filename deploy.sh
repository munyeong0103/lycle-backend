#!/usr/bin/env bash

REPOSITORY=/lycle-backend
cd $REPOSITORY

APP_NAME=lycle-backend-0.0.1-SNAPSHOT.jar
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep 'SNAPSHOT.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 종료할것 없음."
else
  echo "> kill -9 $CURRENT_PID"
  kill $CURRENT_PID
  sleep 5
fi

PROJECT_ROOT="/home/ubuntu/lycle-backend"
JAR_FILE="$PROJECT_ROOT/lycle-backend-0.0.1-SNAPSHOT.jar"

TIME_NOW=$(date +%c)

# build 파일 복사
echo "$TIME_NOW > $JAR_FILE 파일 복사"
cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE

# jar 파일 실행
echo "$TIME_NOW > $JAR_FILE 파일 실행"
nohup java -jar $JAR_FILE &
