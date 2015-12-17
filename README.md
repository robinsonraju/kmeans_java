# A Simple K-means implementation in Java. 

## Build
`mvn package`

## Run
`java -cp kmeans.jar cs286.Kmeans <k> <max-iterations> <delta> <distance = euclidean/cosine> <init = random/partition> <input> <output>`

e.g :

`java -cp target/kmeans.jar cs286.Kmeans 3 10 0.1 euclidean partition src/main/resources/iris-data.txt output.txt`



