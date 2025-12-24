@Service
@RequiredArgsConstructor
@Transactional // 트랜잭션 보장
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository; // User 조회용

    public Long createPost(PostCreateRequestDto requestDto, Long userId) {
        // 1. 작성자 조회 (없으면 예외 발생)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // 2. 게시글 엔티티 생성
        Post post = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .author(user)
                .build();

        // 3. 저장
        return postRepository.save(post).getId();
    }
}