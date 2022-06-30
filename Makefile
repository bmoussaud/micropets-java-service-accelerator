apply-accelerator: undeploy-accelerator deploy-accelerator

deploy-accelerator:
	kapp deploy -c -y -a micropet-java-service-accelerator -f ./k8s

undeploy-accelerator:
	kapp delete -y -a micropet-java-service-accelerator