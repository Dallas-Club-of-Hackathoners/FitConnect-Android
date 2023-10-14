package com.stu.fitconnect.features.sportclubs

import com.stu.fitconnect.features.sportclubs.domain.SportClubSummary


typealias SportClubsPageLoader = suspend (pageIndex: Int) -> List<SportClubSummary>

