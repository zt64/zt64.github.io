if (config.mode == "production") {
    (config.experiments ??= {}).outputModule = true;
    config.output.library = {
        type: "module"
    };
    delete config.output.libraryTarget;
}
config.output.globalObject = "window"
config.output.publicPath = ""