## Tweet-Classification

This was a final year project for [SE @ UU](https://www.ulster.ac.uk/courses/201920/software-engineering-15299) submitted in 2018.

| JDK |
|-----|
|  8  |


_Note_: Of course, there is some manual steps in order to get it running - future me will change the configuration management to use something like Vault.

### What is this?

This was a final year project, more details can be found in the [report](./documentation/FinalReport.pdf) which will outline the purpose of the project. The report will describe in detail the development process and the requirements. Ultimately the project was to use natural language processing to categorise Tweets from Twitter in to rumour and non-rumour categories.

### Getting Started

Before building and running the application it is important to train the classifiers, this can be achieved through running the classifier/src/main/java/twitter/classification/classifier/weka/classifier/NaiveBayesClassifier.java which will serialise a model to disk, and the classifier/src/main/java/twitter/classification/classifier/mallet/classifier/TrainClassifier.java.

Before doing so, some stopwords.txt will need to be added to `classifier/src/main/resources/stopwords/stopwords.txt`, e.g. the following list of stopwords is useful for starting [CoreNLP Stopwords.txt](https://raw.githubusercontent.com/stanfordnlp/CoreNLP/master/data/edu/stanford/nlp/patterns/surface/stopwords.txt)

There will be some additional configuration files need added per service which will be documented below. Once these files are created then a `./gradlew build` can be performed.

#### stream/src/main/resources/configuration.txt

```
TWITTER_OAUTH_ACCESS_KEY=
TWITTER_OAUTH_ACCESS_SECRET=
TWITTER_OAUTH_CONSUMER_KEY=
TWITTER_OAUTH_CONSUMER_SECRET=
QUEUE_HOST=tweet-classification_queue_1
QUEUE_USER=twitter
QUEUE_PASSWORD=password
QUEUE_NAME=tweets
TWITTER_FILTER_LIST=<csv for words to filter>
```

The `TWITTER_*` values can be requested on [https://dev.twitter.com](https://dev.twitter.com) where you'll need to create a developer account and create an application and generate the keys.

#### frontend/src/main/resources/configuration.txt

```
DASHBOARD_OVERVIEW_URI=http://tweet-classification_api_1:8080/dashboard/overview
DASHBOARD_SERVICE_STATUS_URI=http://tweet-classification_api_1:8080/dashboard/services/status
SUGGESTED_SEARCH_RESULTS_URI=http://tweet-classification_api_1:8080/search/suggestions
SEARCH_RESULTS_URI=http://tweet-classification_api_1:8080/search/
TOP_HASHTAGS_RESULTS_URI=http://tweet-classification_api_1:8080/top/hashtags
TOP_USERS_RESULTS_URI=http://tweet-classification_api_1:8080/top/users
```

#### api/src/main/resources/configuration.txt

```
DB_USERNAME=twitter
DB_PASSWORD=password
DB_URL=jdbc:mysql://tweet-classification_db_1:3306/twitter_classification
CLASSIFIER_STATUS_URI=http://tweet-classification_classifier_1:8080/classify/status
PRE_PROCESSOR_STATUS_URI=http://tweet-classification_pre-processor_1:8080/process/status
TWITTER_STREAM_STATUS_URI=http://tweet-classification_stream_1:8080/stream/running
```

#### queue-reader/src/main/resources/configuration.txt

```
QUEUE_USER=twitter
QUEUE_PASSWORD=password
QUEUE_HOST=queue
QUEUE_URI=http://tweet-classification_pre-processor_1:8080/process
HASHTAG_IGNORE_LIST=foo
```

#### classifier/src/main/resources/configuration.txt

```
CLASSIFICATION_WEIGHT_THRESHOLD=0.6
DB_USERNAME=twitter
DB_PASSWORD=password
DB_URL=jdbc:mysql://tweet-classification_db_1:3306/twitter_classification
```

#### pre-processor/src/main/resources/configuration.txt

```
CLASSIFIER_CLASSIFICATION_URI=http://tweet-classification_classifier_1:8080/classify
```

### Building

Once all the configuration files have been created, the services can be built using the gradle wrapper, simply executing `./gradlew build` is sufficient. This will run the tests and build the appropriate JAR files that can be used in the deployment.

### Deployment

The services are ran as Docker containers using `docker-compose`, performing a `docker-compose up -d --build` should build the required services. Onces the services are running hitting localhost:9001/stream/start should start the process of streaming and classifying the Tweets - navigating to `localhost` on a browser will access the frontend.