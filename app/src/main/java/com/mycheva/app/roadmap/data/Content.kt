package com.mycheva.app.roadmap.data

data class Content(val title: String, val items: List<RoadmapItemData>) {
    companion object {
        val WebDevelopment = Content(
            title = "Web Development",
            items = listOf(
                RoadmapItemData("Kegiatan 1", "Lorem ipsum dolor sit amet, Nullam vel orci.", true),
                RoadmapItemData("Kegiatan 2", "Lorem ipsum dolor sit amet, Nullam vel orci.", false),
                RoadmapItemData("Kegiatan 3", "Lorem ipsum dolor sit amet, Nullam vel orci.", true),
                RoadmapItemData("Kegiatan 4", "Lorem ipsum dolor sit amet, Nullam vel orci.", false)
            )
        )

        val UIUX = Content(
            title = "UI/UX",
            items = listOf(
                RoadmapItemData("Kegiatan 1", "Lorem ipsum dolor sit amet, vel orci.", true),
                RoadmapItemData("Kegiatan 2", "Lorem ipsum dolor sit amet, vel orci.", false),
                RoadmapItemData("Kegiatan 3", "Lorem ipsum dolor sit amet, vel orci.", true),
                RoadmapItemData("Kegiatan 4", "Lorem ipsum dolor sit amet, vel orci.", false)
            )
        )

        val Startup = Content(
            title = "Startup",
            items = listOf(
                RoadmapItemData("Kegiatan 1", "Lorem ipsum dolor sit amet, Nullam orci.", true),
                RoadmapItemData("Kegiatan 2", "Lorem ipsum dolor sit amet, Nullam orci.", false),
                RoadmapItemData("Kegiatan 3", "Lorem ipsum dolor sit amet, Nullam orci.", true),
                RoadmapItemData("Kegiatan 4", "Lorem ipsum dolor sit amet, Nullam orci.", false)
            )
        )
    }
}
