#S3 File Merger:
Hi, all! This merger is used to merge a lot of small files like daily logs in your s3 to a large file, which should be more efficient in some advanced analysis.

## Execution Command in AWS Command Line
<code>
aws emr create-cluster --name "S3 File Merger" --use-default-roles --release-label emr-4.1.0 --applications Name=Spark --auto-terminate --log-uri s3://??????/ --visible-to-all-users --instance-groups InstanceCount=1,InstanceGroupType=Master,InstanceType=m3.xlarge InstanceCount=10,InstanceGroupType=Core,InstanceType=m3.xlarge --ec2-attributes SubnetId=subnet-????,KeyName=????KP --configurations file://./sparkConfig.json)
</code>
