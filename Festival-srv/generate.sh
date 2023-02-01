#!/bin/sh

# Source file copied : Fruit

# if something else than $1 is set, then exit.
if [ $# -ne 1 ]; then
    echo "Invalid arguments. "
    echo "\$1 = you should specify the target you want. Example : User. "
    echo "You must set the first letter in upper case. "
    exit 1
fi


root="./src/main/java/festival/srv/"
sampleEmplacement="fruit"
sampleClass="Fruit"
sampleLower="fruit"
sampleUpper="FRUIT"


targetLower=$1
targetLower="$(tr '[:upper:]' '[:lower:]' <<< ${targetLower})"
targetUpper=$1
targetUpper="$(tr '[:lower:]' '[:upper:]' <<< ${targetUpper})"



sampleEntityPath="$root""entity/""$sampleClass"".java"
targetEntityPath="$root""entity/$1.java"

sampleResourcePath="$root""resource/""$sampleClass""Resource.java"
targetResourcePath="$root""resource/$1Resource.java"

sampleServicePath="$root""service/""$sampleClass""Service.java"
targetServicePath="$root""service/$1Service.java"



cp $sampleEntityPath $targetEntityPath
cp $sampleResourcePath $targetResourcePath
cp $sampleServicePath $targetServicePath



gsed -i "s/$sampleClass/$1/g" $targetEntityPath
gsed -i "s/$sampleLower/$targetLower/g" $targetEntityPath

gsed -i "s/$sampleClass/$1/g" $targetResourcePath
gsed -i "s/$sampleLower/$targetLower/g" $targetResourcePath
gsed -i "s/$sampleUpper/$targetUpper/g" $targetResourcePath

gsed -i "s/$sampleClass/$1/g" $targetServicePath
gsed -i "s/$sampleLower/$targetLower/g" $targetServicePath
gsed -i "s/$sampleUpper/$targetUpper/g" $targetServicePath
