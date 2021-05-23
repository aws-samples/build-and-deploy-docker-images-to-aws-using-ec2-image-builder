# Build and Deploy Docker Images to AWS using EC2 Image Builder
In this project, we walk through the process of building a Docker image and deploying the image to Amazon ECR, share some security best practices, and demonstrate deploying a Docker image to Amazon Elastic Container Service (Amazon ECS). Additionally, we dive deep into building Docker images following modern principles. The project we create in this post addresses a use case in which an organization needs an automated workflow for building, distributing, and deploying Docker images. With Image Builder, we build and deploy Docker images and test our image locally that we have created with our Image Builder pipeline.

## Motivation
A common scenario you may face is how to build Docker images that can be utilized throughout your organization. You may already have existing processes that you’re looking to modernize. You may be looking for a streamlined, managed approach so you can reduce the overhead of operating your own workflows. Additionally, if you’re new to containers, you may be seeking an end-to-end process you can use to deploy containerized workloads. With either case, there is need for a modern, streamlined approach to centralize the configuration and distribution of Docker images. This post demonstrates how to build a secure end-to-end workflow for building secure Docker images.


## Tech/framework used
For this post, we use the following services:

    EC2 Image Builder – Image Builder is a fully managed AWS service that makes it easy to automate the creation, management, and deployment of customized, secure, and up-to-date server images that are pre-installed and pre-configured with software and settings to meet specific IT standards.

    Amazon ECR – Amazon ECR is an AWS managed container image registry service that is secure, scalable, and reliable.

    CodeCommit – AWS CodeCommit is a fully-managed source control service that hosts secure Git-based repositories.

    AWS KMS – Amazon Key Management Service (AWS KMS) is a fully managed service for creating and managing cryptographic keys. These keys are natively integrated with most AWS services. You use a KMS key in this post to encrypt resources.

    Amazon S3 – Amazon Simple Storage Service (Amazon S3) is an object storage service utilized for storing and encrypting data. We use Amazon S3 to store our configuration files.

    AWS CloudFormation – AWS CloudFormation allows you to use domain-specific languages or simple text files to model and provision, in an automated and secure manner, all the resources needed for your applications across all Regions and accounts. You can deploy AWS resources in a safe, repeatable manner, and automate the provisioning of infrastructure.


## Features
You use the following CloudFormation templates to deploy several resources:

    vpc.yml – Contains all the core networking configuration. It deploys the VPC, two private subnets, two public subnets, and the route tables. The private subnets utilize a NAT gateway to communicate to the internet. The public subnets have full outbound access to the internet gateway.
    kms.yml – Contains the AWS Key Management Service (AWS KMS) configuration that we use for encrypting resources. The KMS key policy is also configured in this template.
    s3-iam-config.yml – Contains the S3 bucket and IAM roles we use with our Image Builder pipeline.
    docker-image-builder.yml – Contains the configuration for the Image Builder pipeline that we use to build Docker images.


## How to use?
To deploy this project follow the step by step instructions found here: [https://aws.amazon.com/blogs/devops/build-and-deploy-docker-images-to-aws-using-ec2-image-builder/]

## Security

See [CONTRIBUTING](CONTRIBUTING.md#security-issue-notifications) for more information.

## License

This library is licensed under the MIT-0 License. See the LICENSE file.
