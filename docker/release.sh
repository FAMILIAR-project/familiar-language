#!/bin/bash

JAR=familiar.jar
VERSION=1.2
DOCKER_ID=petitroll
LOCAL_NAME=familiar
PUBLIC_IMAGE=$DOCKER_ID/$LOCAL_NAME:$VERSION

if [ ! -f $JAR ]
then
  echo "File $JAR does not existing, preparing release"
  ./prepare.sh
fi

docker build -t $LOCAL_NAME .
docker tag $LOCAL_NAME $PUBLIC_IMAGE
docker push $PUBLIC_IMAGE
