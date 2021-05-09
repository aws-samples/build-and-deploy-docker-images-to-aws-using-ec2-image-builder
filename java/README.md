<!---
Copyright 2021 Amazon.com, Inc. or its affiliates. All Rights Reserved.
Permission is hereby granted, free of charge, to any person obtaining a copy of this
software and associated documentation files (the "Software"), to deal in the Software
without restriction, including without limitation the rights to use, copy, modify,
merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
Java Dockerfile
-->

### Notes:
```
Reference: https://github.com/aws/aws-lambda-java-libs/blob/master/aws-lambda-java-runtime-interface-client/README.md
Replace <GIT_PUBLIC_URL> <GIT_PUBLIC_REPO_DIRECTORY> with the final aws sample repo information.
```

#### Build Java Docker Image
**RUN:** 
```
docker build -t java_0.2 -f java/Dockerfile .
```

#### Run Java Docker Image default Capabilities
**RUN:**
```
docker run -dp 8090:8090 --name java_hello_world -it <docker_image_id> sh
```