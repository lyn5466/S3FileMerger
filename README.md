A S3 file merger: 
aws emr create-cluster --name "S3 File Merger" --use-default-roles --release-label emr-4.1.0 --applications Name=Spark --auto-terminate --log-uri s3://??????/ --visible-to-all-users --instance-groups InstanceCount=1,InstanceGroupType=Master,InstanceType=m3.xlarge InstanceCount=10,InstanceGroupType=Core,InstanceType=m3.xlarge --ec2-attributes SubnetId=subnet-????,KeyName=????KP --configurations file://./sparkConfig.json)
