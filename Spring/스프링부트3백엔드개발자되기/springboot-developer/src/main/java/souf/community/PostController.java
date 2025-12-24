@RestController
@RequestMapping("/api/community/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * @apiNote POST /api/community/posts
     * 게시글 생성
     * @param userDetails 로그인한 유저 정보
     */
    @PostMapping
    public ResponseEntity<Long> createPost(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody PostCreateRequestDto requestDto) {

        // 로그인된 사용자의 ID를 서비스로 전달
        Long postId = postService.createPost(requestDto, userDetails.getUserId());

        // 생성된 리소스의 위치(URI)를 헤더에 담거나, ID를 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(postId);
    }
}