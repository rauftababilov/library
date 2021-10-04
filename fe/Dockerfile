FROM node:16-alpine3.11 as REACT_BUILD
WORKDIR /app
COPY package.json /app/package.json
RUN npm install --only=prod
COPY . /app
ARG REACT_APP_API_URL
ENV REACT_APP_API_URL=${REACT_APP_API_URL}
ARG REACT_API_URL
ENV REACT_API_URL=${REACT_API_URL}
RUN npm run build

FROM nginx:1.21.3-alpine
COPY --from=REACT_BUILD /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
