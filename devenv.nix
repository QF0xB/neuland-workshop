{ pkgs, ... }:
let
  javaPackage = pkgs.jdk25;
in
{
  packages = with pkgs; [
    javaPackage
    jq
    curl
    wget
    git
    openssl
    httpie
    yq-go
    postgresql
    docker
    docker-compose
    kcat
    kafkactl
    minio-client
  ];

  languages.java = {
    enable = true;
    jdk.package = javaPackage;
    maven.enable = true;
  };

  enterShell = ''
    echo
    echo "foxshield developer shell ready"
    echo "Java:   ${javaPackage}"
    echo "Gradle: prefer wrapper pinned to > 9 on root"
    echo "Infra:  devenv -f infrastructure/dev/devenv.nix up"
    echo
  '';
}
