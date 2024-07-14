# Stage 1: Build the React application
FROM node:16-alpine AS build

WORKDIR /app

# Install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy the rest of the application code
COPY . .

# Ensure permissions for node_modules/.bin
RUN chmod +x node_modules/.bin/react-scripts

# Build the application
RUN npm run build

# Stage 2: Serve the React application
FROM nginx:alpine

COPY --from=build /app/build /usr/share/nginx/html

# Expose port 80 to the outside world
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
