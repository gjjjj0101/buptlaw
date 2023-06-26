all: container save

container:
	docker build -t buptlaw-frontend .

save:
	docker save -o buptlaw-frontend.tar buptlaw-frontend