package backend.enums;

public enum API_PATHS {

    logo_Path("data.1027.logo"),
    techDoc_Path("data.1027.urls.technical_doc"),
    symbol_Path("data.1027.symbol"),
    date_Path("data.1027.date_added"),
    platform_Path("data.1027.platform"),
    mineableTag_Path("data.1027.tags[0]"),
    quotePrice_Path("data.quote.2832.price");

    private String path;

    public String getConfigPath() {
        return path;
    }

    API_PATHS(String str)
    {
        path = str;
    }



}
