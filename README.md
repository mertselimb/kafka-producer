##### All should work if there are problems please feel free to contact me at: mertselimb@gmail.com

##### Because of time problems (finals and thesis) the dashboard is not fully complete but it is stil real-time

### Important note: Producer uses the format that is in the test.txt. Please do not change format nor add out of format text to it otherwise it will broke the consumer.
If that happens use the clean topic command and then create the topic again before starting.

## To use:
### Step: 1
To pull my repos 

https://github.com/mertselimb/kafka-consumer.git

https://github.com/mertselimb/kafka-producer.git

### Step: 2
To pull docker img and run (you can use landoop at localhost:3030)

docker run --rm -it -p 2181:2181 -p 3030:3030 -p 8081:8081 -p 8082:8082 -p 8083:8083 -p 9092:9092 -e ADV_HOST=127.0.0.1 landoop/fast-data-dev

### Step: 3
For ssh

docker run --rm -it --net=host landoop/fast-data-dev bash

### Step: 4
For creating topic (not needed)(in ssh)

kafka-topics --zookeeper localhost:2181 --create --topic teb --partitions 1 --replication-factor 1

### Step: 5
Start consumer and producer

### Step: Optional(If out of format may broke consumer)
For producer (in ssh)

kafka-console-producer --broker-list localhost:9092 --topic teb

### Step: Optional
For clean up of topic (in ssh)

kafka-topics --zookeeper localhost:2181  --delete --topic teb




## For outputs

### For db data after consumer is up
http://localhost:8084/all

### For runtime list after consumer is up
http://localhost:8084/list

### For the city data on the runtime after consumer is up
http://localhost:8084/graph

