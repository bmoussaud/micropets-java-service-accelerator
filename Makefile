apply-accelerator: publish undeploy-accelerator deploy-accelerator

deploy-accelerator:
	kapp deploy -c -y -a micropet-java-service-accelerator -f ./k8s

undeploy-accelerator:
	kapp delete -y -a micropet-java-service-accelerator

describe:
	kubectl describe Accelerator micropet-java-service-accelerator -n accelerator-system

status:
	kubectl tree Accelerator micropet-java-service-accelerator -n accelerator-system

publish:
	git add -A  && git commit -m "accelerator" && git push