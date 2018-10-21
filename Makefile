version = 0.0
jar_file = server-$(version).jar
jar_path_file = server/build/libs/${jar_file}

all: 

.PHONY: run
run:
	java -jar $(jar_path_file)

.PHONY: clean
clean:
	gradle clean

.PHONY: test
test:
	gradle test

.PHONY: build
build:
	gradle build
