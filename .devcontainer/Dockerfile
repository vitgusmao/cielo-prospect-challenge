FROM mcr.microsoft.com/devcontainers/java:1-17-bullseye

ENV DEBIAN_FRONTEND noninteractive

ARG INSTALL_MAVEN="true"
ARG MAVEN_VERSION=""

ARG NODE_VERSION="18"


RUN su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install maven \"${MAVEN_VERSION}\""

RUN curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.5/install.sh | bash

RUN export NVM_DIR="$([ -z "${XDG_CONFIG_HOME-}" ] && printf %s "${HOME}/.nvm" || printf %s "${XDG_CONFIG_HOME}/nvm")"

RUN echo 'source $NVM_DIR.sh' >> /root/.bashrc
SHELL ["/bin/bash", "-c", "-l"]

RUN su vscode -c "source "$NVM_DIR/nvm.sh" && nvm install ${NODE_VERSION}"

# [Optional] Uncomment this section to install additional OS packages.
# RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
#     && apt-get -y install --no-install-recommends <your-package-list-here>

# [Optional] Uncomment this line to install global node packages.
# RUN su vscode -c "source /usr/local/share/nvm/nvm.sh && npm install -g <your-package-here>" 2>&1
