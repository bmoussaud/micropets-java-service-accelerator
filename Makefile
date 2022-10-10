TANZU_ACCELERATOR=tanzu accelerator
ACCELERATOR_NAME=micropets-java-service-accelerator
REGISTRY=akseutap2registry.azurecr.io

push-accelerator: 
	$(TANZU_ACCELERATOR) push --local-path . --source-image $(REGISTRY)/$(ACCELERATOR_NAME) 

deploy-git-accelerator:
#kapp deploy -c -y -a micropet-java-service-accelerator -f ./k8s
	$(TANZU_ACCELERATOR) create $(ACCELERATOR_NAME) --git-repo https://github.com/bmoussaud/micropets-java-service-accelerator --git-branch main --interval 5s

deploy-source-accelerator:
	$(TANZU_ACCELERATOR) create $(ACCELERATOR_NAME) --local-path . --source-image $(REGISTRY)/$(ACCELERATOR_NAME)  --interval 1s --secret-ref regsecrets

undeploy-accelerator:
#kapp delete -y -a micropet-java-service-accelerator
	$(TANZU_ACCELERATOR) delete $(ACCELERATOR_NAME)

describe:
	kubectl describe Accelerator $(ACCELERATOR_NAME) -n accelerator-system

status:
	kubectl tree Accelerator $(ACCELERATOR_NAME) -n accelerator-system

publish:
	git add -A  && git commit -m "accelerator" && git push

generate: 
	-rm -rf generated target
	$(TANZU_ACCELERATOR) push --local-path . --source-image $(REGISTRY)/$(ACCELERATOR_NAME) 
	mkdir generated
	$(TANZU_ACCELERATOR) generate $(ACCELERATOR_NAME) --server-url http://localhost:8877 --output-dir generated --options-file generate.json
	cd generated && unzip *.zip 
	cat generated/$(ACCELERATOR_NAME)/accelerator-log.md

proxy-accelerator:
	kubectl port-forward service/acc-server -n accelerator-system 8877:80
	
deploy-secret:
	kubectl create secret docker-registry regsecrets --namespace accelerator-system --docker-server=$(REGISTRY) --docker-username=$(INSTALL_REGISTRY_USERNAME) --docker-password=$(INSTALL_REGISTRY_PASSWORD)