package org.sopt.play.ui.post.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.ui.theme.Gray2
import org.sopt.play.ui.theme.Gray3
import org.sopt.play.ui.theme.Gray5
import org.sopt.play.ui.theme.PlaySoptTheme
import org.sopt.play.ui.theme.PlayTypography
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester

@Composable
fun PostSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged{ isFocused = it.isFocused }
            .border(
                width = 2.dp,
                color = if (isFocused) Gray5 else Gray2,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        textStyle = PlayTypography.m18.copy(color = Gray5),
        singleLine = true,
        cursorBrush = SolidColor(Gray5),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearch() }
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.heightIn(min = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = "검색어를 입력하세요",
                            color = Gray2,
                            maxLines = 1,
                            style = PlayTypography.m18
                        )
                    }

                    innerTextField()
                }

                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "게시글 검색",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            indication = null,
                            onClick = onSearch
                        ),
                    tint = Gray3
                )
            }
        }
    )
}

@Preview(showBackground = true, widthDp = 372)
@Composable
private fun PostSearchFieldPreview() {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(focusRequester) {
        focusRequester.requestFocus()
    }

    PlaySoptTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            PostSearchField(
                value = "",
                onValueChange = {},
                onSearch = {}
            )

            PostSearchField(
                value = "솝트",
                onValueChange = {},
                onSearch = {}
            )

            PostSearchField(
                value = "솝트",
                onValueChange = {},
                onSearch = {},
                modifier = Modifier.focusRequester(focusRequester)
            )
        }
    }
}
