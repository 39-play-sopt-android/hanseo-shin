package org.sopt.play.ui.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.ui.component.BottomTab
import org.sopt.play.ui.component.HomeTopBar
import org.sopt.play.ui.component.PlayBottomBar
import org.sopt.play.ui.component.PlayButton
import org.sopt.play.ui.component.PlayChip
import org.sopt.play.ui.component.PlayMenu
import org.sopt.play.ui.component.PlayTextField
import org.sopt.play.ui.component.PlayTextFieldType
import org.sopt.play.ui.component.ProductCard
import org.sopt.play.ui.favorite.FavoriteScreen
import org.sopt.play.ui.home.HomeScreen
import org.sopt.play.ui.home.HomeUiState
import org.sopt.play.ui.home.component.HomeBanner
import org.sopt.play.ui.home.component.HomeCategory
import org.sopt.play.ui.home.component.HomeCategoryRow
import org.sopt.play.ui.home.component.HomeMenuRow
import org.sopt.play.ui.home.component.HomeMenuType
import org.sopt.play.ui.home.component.HomeProductGrid
import org.sopt.play.ui.home.component.HomeRecommendationTitle
import org.sopt.play.ui.home.model.HomeProduct
import org.sopt.play.ui.login.LoginScreen
import org.sopt.play.ui.login.LoginUiState
import org.sopt.play.ui.main.MainScreen
import org.sopt.play.ui.mypage.MyPageScreen
import org.sopt.play.ui.register.RegisterUiState
import org.sopt.play.ui.register.RegisterScreen
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.White

// 화면 Preview: 기본 크기와 큰 글꼴을 함께 표시

@PlayScreenPreviewAnnotation
@Composable
private fun LoginScreenPreview() {
    PlaySoptTheme {
        LoginScreen(
            uiState = LoginUiState(),
            onEmailChange = {},
            onPasswordChange = {},
            onLoginClick = { _, _ -> },
            onRegisterClick = {}
        )
    }
}

@PlayScreenPreviewAnnotation
@Composable
private fun RegisterScreenPreview() {
    PlaySoptTheme {
        RegisterScreen(
            uiState = RegisterUiState(),
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onPasswordConfirmChange = {},
            onRegisterClick = { _, _ -> }
        )
    }
}

@PlayScreenPreviewAnnotation
@Composable
private fun MainScreenPreview() {
    PlaySoptTheme {
        MainScreen()
    }
}

@PlayScreenPreviewAnnotation
@Composable
private fun HomeScreenPreview() {
    PlaySoptTheme {
        HomeScreen(
            uiState = HomeUiState(),
            onMenuClick = {},
            onCategoryClick = {},
            onFavoriteClick = {},
        )
    }
}

@PlayScreenPreviewAnnotation
@Composable
private fun FavoriteScreenPreview() {
    PlaySoptTheme {
        FavoriteScreen()
    }
}

@PlayScreenPreviewAnnotation
@Composable
private fun MyPageScreenPreview() {
    PlaySoptTheme {
        MyPageScreen()
    }
}

// 컴포넌트 Preview에서 사용하는 공통 배경과 간격

@Composable
private fun PreviewContent(
    content: @Composable () -> Unit
) {
    PlaySoptTheme {
        Column(
            modifier = Modifier
                .background(White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            content()
        }
    }
}

// 버튼: 활성 / 비활성

@Preview(name = "버튼 상태", showBackground = true, widthDp = 360)
@Composable
private fun ButtonStatesPreview() {
    PreviewContent {
        PlayButton(
            text = "로그인",
            onClick = {},
            enabled = true
        )

        PlayButton(
            text = "로그인",
            onClick = {},
            enabled = false
        )
    }
}

// 텍스트 필드: 이름 / 빈 값 / 입력됨 / 오류 / 비밀번호

@Preview(name = "텍스트 필드 상태", showBackground = true, widthDp = 360)
@Composable
private fun TextFieldStatesPreview() {
    PreviewContent {
        PlayTextField(
            value = "",
            onValueChange = {},
            label = "이름",
            placeholder = "홍길동",
            type = PlayTextFieldType.Text
        )

        PlayTextField(
            value = "",
            onValueChange = {},
            label = "이메일 주소",
            placeholder = "abc@email.com",
            type = PlayTextFieldType.Email
        )

        PlayTextField(
            value = "abc@email.com",
            onValueChange = {},
            label = "이메일 주소",
            placeholder = "abc@email.com",
            type = PlayTextFieldType.Email
        )

        PlayTextField(
            value = "abc.com",
            onValueChange = {},
            label = "이메일 주소",
            placeholder = "abc@email.com",
            type = PlayTextFieldType.Email,
            errorMessage = "올바른 이메일을 입력해주세요."
        )

        PlayTextField(
            value = "123456",
            onValueChange = {},
            label = "비밀번호",
            placeholder = "6자 이상의 비밀번호",
            type = PlayTextFieldType.Password
        )

        PlayTextField(
            value = "123",
            onValueChange = {},
            label = "비밀번호",
            placeholder = "6자 이상의 비밀번호",
            type = PlayTextFieldType.Password,
            errorMessage = "비밀번호는 6자 이상 입력해주세요."
        )

        PlayTextField(
            value = "654321",
            onValueChange = {},
            label = "비밀번호 확인",
            placeholder = "6자 이상의 비밀번호",
            type = PlayTextFieldType.Password,
            errorMessage = "비밀번호와 동일하게 입력해주세요."
        )
    }
}

// 메뉴: 선택 / 미선택

@Preview(name = "메뉴 상태", showBackground = true)
@Composable
private fun MenuStatesPreview() {
    PreviewContent {
        Row {
            PlayMenu(
                text = "메뉴",
                selected = true,
                onClick = {}
            )

            PlayMenu(
                text = "메뉴",
                selected = false,
                onClick = {}
            )
        }
    }
}

// 칩: 선택 / 미선택

@Preview(name = "칩 상태", showBackground = true)
@Composable
private fun ChipStatesPreview() {
    PreviewContent {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            PlayChip(
                text = "전체",
                onClick = {},
                selected = true
            )

            PlayChip(
                text = "전체",
                onClick = {},
                selected = false
            )
        }
    }
}

// 상품 카드: 짧은 / 긴 텍스트, 좋아요 선택 / 미선택

@Preview(name = "상품 카드 상태", showBackground = true, widthDp = 360)
@Composable
private fun ProductCardStatesPreview() {
    PreviewContent {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ProductCard(
                product = HomeProduct(
                    id = 1,
                    imageRes = R.drawable.img_home_product_1,
                    storeName = "스토어명",
                    productName = "상품명",
                    discountRate = 36,
                    price = "28,800",
                    isFavorite = false
                ),
                modifier = Modifier.weight(1f),
                onFavoriteClick = {},
            )

            ProductCard(
                product = HomeProduct(
                    id = 2,
                    imageRes = R.drawable.img_home_product_2,
                    storeName = "한 줄을 넘어가는 아주 긴 스토어명",
                    productName = "한 줄을 넘어가는 아주 긴 상품명입니다",
                    discountRate = 50,
                    price = "26,820",
                    isFavorite = true
                ),
                modifier = Modifier.weight(1f),
                onFavoriteClick = {},
            )
        }
    }
}

// 바텀바: 각 탭 선택 상태

@Preview(name = "바텀바 상태", showBackground = true, widthDp = 360)
@Composable
private fun BottomBarStatesPreview() {
    PreviewContent {
        BottomTab.entries.forEach { tab ->
            PlayBottomBar(
                selectedTab = tab,
                onTabClick = {}
            )
        }
    }
}

@Preview(name = "홈 탑바", showBackground = true, widthDp = 360)
@Composable
private fun HomeTopBarPreview() {
    PlaySoptTheme {
        HomeTopBar(onSearchClick = {})
    }
}

@Preview(name = "홈 메뉴 목록", showBackground = true, widthDp = 360)
@Composable
private fun HomeMenuRowPreview() {
    PlaySoptTheme {
        HomeMenuRow(
            selectedMenu = HomeMenuType.HOME,
            onMenuClick = {}
        )
    }
}

@Preview(name = "홈 배너", showBackground = true, widthDp = 380)
@Composable
private fun HomeBannerPreview() {
    PlaySoptTheme {
        HomeBanner(
            images = listOf(
                R.drawable.img_home_banner_1,
                R.drawable.img_home_banner_2,
                R.drawable.img_home_banner_3
            )
        )
    }
}

@Preview(name = "추천 제목", showBackground = true, widthDp = 360)
@Composable
private fun RecommendationTitlePreview() {
    PreviewContent {
        HomeRecommendationTitle(name = "김솝트")
    }
}

@Preview(name = "카테고리 목록", showBackground = true, widthDp = 360)
@Composable
private fun CategoryRowPreview() {
    PlaySoptTheme {
        HomeCategoryRow(
            selectedCategory = HomeCategory.ALL,
            onCategoryClick = {}
        )
    }
}

@Preview(
    name = "상품 그리드",
    showBackground = true,
    widthDp = 360,
    heightDp = 700
)
@Composable
private fun ProductGridPreview() {
    PlaySoptTheme {
        HomeProductGrid(
            products = previewProducts,
            onFavoriteClick = {},
        )
    }
}

// Preview 전용 상품 데이터

private val previewProducts = listOf(
    HomeProduct(
        id = 1,
        imageRes = R.drawable.img_home_product_1,
        storeName = "트렌디어패럴",
        productName = "[카프리1위🏆/5부,7부,9부] 팬츠",
        discountRate = 36,
        price = "28,800"
    ),
    HomeProduct(
        id = 2,
        imageRes = R.drawable.img_home_product_2,
        storeName = "슬로우앤드",
        productName = "[🍁가을/3기장선택!] made 팬츠",
        discountRate = 50,
        price = "26,820",
        isFavorite = true
    ),
    HomeProduct(
        id = 3,
        imageRes = R.drawable.img_home_product_3,
        storeName = "여넴",
        productName = "보울7부티셔츠",
        discountRate = 10,
        price = "17,100"
    ),
    HomeProduct(
        id = 4,
        imageRes = R.drawable.img_home_product_4,
        storeName = "슬로우앤드",
        productName = "[오픈할인중] made. 도브 셔츠",
        discountRate = 40,
        price = "35,200"
    )
)