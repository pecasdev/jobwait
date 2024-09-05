FROM node:18.13.0-bullseye AS builder
WORKDIR /builder

COPY . .

RUN npm install
RUN npm run build

###########################################################

FROM node:18.13.0-bullseye AS image
WORKDIR /app

COPY --from=builder builder/build build

RUN npm install -g serve

ENTRYPOINT ["serve", "-s", "build"]
