package com.wzy.bbcnews.model;

import java.util.List;

/**
 * Created by zy on 2016/3/29.
 */
public class BBCNews {
    /**
     * eTag : 56d716b1533d667439014ae19b53fcab
     * primaryType : bbc.mobile.news.item
     * secondaryType : bbc.mobile.news.group.top-story
     * content : {"id":"/cps/news/world-europe-35911401","type":"bbc.mobile.news.item","format":"bbc.mobile.news.format.textual","language":"en-gb","lastUpdated":1459183022000,"site":"/news","name":"Brussels attacks: Belgium releases terror murder suspect","summary":"A man known as Faycal C, the only person arrested and charged with involvement in the Brussels attacks, is released for lack of evidence.","shortName":"Belgium frees Brussels bombing suspect","iStatsCounter":"news.world.europe.story.35911401.page","relations":[{"primaryType":"bbc.mobile.news.image","secondaryType":"bbc.mobile.news.placement.index","content":{"id":"/cpsprodpb/17A9F/production/_88972969_88972968.jpg","type":"bbc.mobile.news.image","width":976,"height":549,"href":"http://c.files.bbci.co.uk/17A9F/production/_88972969_88972968.jpg","altText":"People tend to tributes in the Place de la Bourse in Brussels, 28 March","copyrightHolder":"AP","relations":[]}},{"primaryType":"bbc.mobile.news.video","secondaryType":"bbc.mobile.news.placement.primary","content":{"id":"/video/p03p9fnj","type":"bbc.mobile.news.video","duration":33000,"caption":"Belgian police have released CCTV footage of a man wanted for the attack on Zaventem airport","externalId":"p03p9fnj","isEmbeddable":false,"isAvailable":true,"isLive":false,"relations":[{"primaryType":"bbc.mobile.news.image","secondaryType":"bbc.mobile.news.placement.poster","content":{"id":"/cpsprodpb/79DC/production/_88969113_88969112.jpg","type":"bbc.mobile.news.image","width":640,"height":360,"href":"http://c.files.bbci.co.uk/79DC/production/_88969113_88969112.jpg","altText":"Still from the CCTV footage","copyrightHolder":"Police Federale","relations":[]}}]}},{"primaryType":"bbc.mobile.news.collection","secondaryType":"bbc.mobile.news.home_section","content":{"id":"/cps/news/world/europe","type":"bbc.mobile.news.collection","format":"bbc.mobile.news.cps.idx","language":"en-gb","lastUpdated":1459175113000,"site":"/news","name":"Europe","summary":"Get the latest European news from BBC News in Europe: headlines, features and analysis from BBC correspondents across the European Union, EU, and the rest of Europe.","iStatsCounter":"news.world.europe.page","iStatsLabels":{"page_type":"index","cps_asset_id":"10059386"},"allowAdvertising":true,"relations":[],"shareUrl":"http://www.bbc.co.uk/news/world/europe","eTag":"0db5ee4187329b5b836a175a719fedd5"}}],"shareUrl":"http://www.bbc.co.uk/news/world-europe-35911401","iStatsLabels":{"page_type":"story","cps_asset_id":"35911401"}}
     */

    private List<RelationsEntity> relations;

    public List<RelationsEntity> getRelations() {
        return relations;
    }

    public void setRelations(List<RelationsEntity> relations) {
        this.relations = relations;
    }

    public static class RelationsEntity {
        /**
         * id : /cps/news/world-europe-35911401
         * type : bbc.mobile.news.item
         * format : bbc.mobile.news.format.textual
         * language : en-gb
         * lastUpdated : 1459183022000
         * site : /news
         * name : Brussels attacks: Belgium releases terror murder suspect
         * summary : A man known as Faycal C, the only person arrested and charged with involvement in the Brussels attacks, is released for lack of evidence.
         * shortName : Belgium frees Brussels bombing suspect
         * iStatsCounter : news.world.europe.story.35911401.page
         * relations : [{"primaryType":"bbc.mobile.news.image","secondaryType":"bbc.mobile.news.placement.index","content":{"id":"/cpsprodpb/17A9F/production/_88972969_88972968.jpg","type":"bbc.mobile.news.image","width":976,"height":549,"href":"http://c.files.bbci.co.uk/17A9F/production/_88972969_88972968.jpg","altText":"People tend to tributes in the Place de la Bourse in Brussels, 28 March","copyrightHolder":"AP","relations":[]}},{"primaryType":"bbc.mobile.news.video","secondaryType":"bbc.mobile.news.placement.primary","content":{"id":"/video/p03p9fnj","type":"bbc.mobile.news.video","duration":33000,"caption":"Belgian police have released CCTV footage of a man wanted for the attack on Zaventem airport","externalId":"p03p9fnj","isEmbeddable":false,"isAvailable":true,"isLive":false,"relations":[{"primaryType":"bbc.mobile.news.image","secondaryType":"bbc.mobile.news.placement.poster","content":{"id":"/cpsprodpb/79DC/production/_88969113_88969112.jpg","type":"bbc.mobile.news.image","width":640,"height":360,"href":"http://c.files.bbci.co.uk/79DC/production/_88969113_88969112.jpg","altText":"Still from the CCTV footage","copyrightHolder":"Police Federale","relations":[]}}]}},{"primaryType":"bbc.mobile.news.collection","secondaryType":"bbc.mobile.news.home_section","content":{"id":"/cps/news/world/europe","type":"bbc.mobile.news.collection","format":"bbc.mobile.news.cps.idx","language":"en-gb","lastUpdated":1459175113000,"site":"/news","name":"Europe","summary":"Get the latest European news from BBC News in Europe: headlines, features and analysis from BBC correspondents across the European Union, EU, and the rest of Europe.","iStatsCounter":"news.world.europe.page","iStatsLabels":{"page_type":"index","cps_asset_id":"10059386"},"allowAdvertising":true,"relations":[],"shareUrl":"http://www.bbc.co.uk/news/world/europe","eTag":"0db5ee4187329b5b836a175a719fedd5"}}]
         * shareUrl : http://www.bbc.co.uk/news/world-europe-35911401
         * iStatsLabels : {"page_type":"story","cps_asset_id":"35911401"}
         */

        private ContentEntity content;

        public ContentEntity getContent() {
            return content;
        }

        public void setContent(ContentEntity content) {
            this.content = content;
        }

        public static class ContentEntity {
            private String name;
            private String summary;
            private String shortName;
            private String shareUrl;

            public String getLastUpdated() {
                return lastUpdated;
            }

            public void setLastUpdated(String lastUpdated) {
                this.lastUpdated = lastUpdated;
            }

            private String lastUpdated;
            /**
             * primaryType : bbc.mobile.news.image
             * secondaryType : bbc.mobile.news.placement.index
             * content : {"id":"/cpsprodpb/17A9F/production/_88972969_88972968.jpg","type":"bbc.mobile.news.image","width":976,"height":549,"href":"http://c.files.bbci.co.uk/17A9F/production/_88972969_88972968.jpg","altText":"People tend to tributes in the Place de la Bourse in Brussels, 28 March","copyrightHolder":"AP","relations":[]}
             */

            private List<RelationsEntity1> relations;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getShortName() {
                return shortName;
            }

            public void setShortName(String shortName) {
                this.shortName = shortName;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public List<RelationsEntity1> getRelations() {
                return relations;
            }

            public void setRelations(List<RelationsEntity1> relations) {
                this.relations = relations;
            }

            public static class RelationsEntity1 {
                /**
                 * id : /cpsprodpb/17A9F/production/_88972969_88972968.jpg
                 * type : bbc.mobile.news.image
                 * width : 976
                 * height : 549
                 * href : http://c.files.bbci.co.uk/17A9F/production/_88972969_88972968.jpg
                 * altText : People tend to tributes in the Place de la Bourse in Brussels, 28 March
                 * copyrightHolder : AP
                 * relations : []
                 */

                private ContentEntity1 content;

                public ContentEntity1 getContent() {
                    return content;
                }

                public void setContent(ContentEntity1 content) {
                    this.content = content;
                }

                public static class ContentEntity1 {
                    private String id;
                    private String href;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getHref() {
                        return href;
                    }

                    public void setHref(String href) {
                        this.href = href;
                    }
                }
            }
        }
    }
}
