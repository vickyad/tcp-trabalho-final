package Test;

import static org.junit.Assert.*;
import Services.MusicValidationService;
import org.junit.Test;

public class MusicValidationServiceTest {
    MusicValidationService musicValidationService = new MusicValidationService();

    @Test
    public void testEmptyText() {
        assertFalse(musicValidationService.validateString(""));
        assertFalse(musicValidationService.validateString("   "));
        assertFalse(musicValidationService.validateString("\n"));
    }

    @Test
    public void testTooLongText() {
        assertFalse(musicValidationService.validateString("""
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ac feugiat ex, eu laoreet nisl. Cras non egestas eros, ac lacinia enim. Nam hendrerit fermentum egestas. Aliquam nec accumsan felis. Etiam volutpat enim nec arcu maximus cursus. Donec aliquam pharetra ullamcorper. Morbi vel consectetur mauris. Etiam maximus eros urna, a elementum nunc egestas a. Fusce ac dapibus tortor, sed consectetur erat. Fusce imperdiet nulla sit amet porta dignissim. Morbi id odio sodales, malesuada velit sit amet, mollis turpis. Ut consequat lacinia urna convallis maximus. Aliquam arcu velit, cursus eget tempor vitae, varius at mauris. Fusce eu efficitur nisi. Proin blandit arcu vel placerat laoreet. Cras eget venenatis nibh.

                In facilisis diam ac erat malesuada, a molestie enim semper. Sed justo dui, ullamcorper non ante sit amet, ullamcorper venenatis augue. Ut sodales pulvinar urna. Curabitur dignissim mi quis pharetra iaculis. Mauris efficitur convallis tempor. Sed aliquet ante et augue consequat condimentum. Ut sed ipsum non dolor dapibus dignissim nec vitae justo. Nullam sodales accumsan interdum.

                Ut nec posuere nisi. Fusce condimentum tortor vel luctus pretium. Duis eu massa risus. Donec convallis laoreet augue, congue tincidunt tellus convallis in. Aenean sit amet felis in nisi consectetur porta. Vestibulum ac eros a orci dignissim rutrum. Maecenas nulla velit, mattis non consequat ac, commodo quis ipsum. Suspendisse aliquam, justo quis malesuada suscipit, est risus volutpat nunc, eu malesuada orci turpis in turpis.

                Donec vel tristique nisi. Aenean varius, dolor non euismod iaculis, elit nisl molestie nulla, eget auctor neque justo non mauris. Sed eu vulputate arcu. Ut aliquam, nibh vitae placerat fringilla, sapien orci varius mauris, fringilla blandit arcu neque sed augue. Praesent ultrices pretium arcu eu convallis. Donec aliquam dignissim nisl, non volutpat odio pretium rutrum. Fusce a quam egestas, dictum diam nec, vulputate neque. Nam eu fringilla nunc, quis aliquam ligula. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Proin vel tempus lectus. Nunc massa ante, imperdiet nec rutrum at, vulputate ut nunc. Vivamus in orci ac nulla dictum ornare eget quis tellus. Etiam erat augue, euismod nec laoreet sit amet, blandit ac massa. Curabitur placerat mi eu nisi sagittis, eget sagittis quam blandit. Pellentesque convallis posuere venenatis."""));
    }

    @Test
    public void testValidStringInput() {
        assertTrue(musicValidationService.validateString("This string is just a test"));
        assertTrue(musicValidationService.validateString("""
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ac feugiat ex, eu laoreet nisl. Cras non egestas eros, ac lacinia enim. Nam hendrerit fermentum egestas. Aliquam nec accumsan felis. Etiam volutpat enim nec arcu maximus cursus. Donec aliquam pharetra ullamcorper. Morbi vel consectetur mauris. Etiam maximus eros urna, a elementum nunc egestas a. Fusce ac dapibus tortor, sed consectetur erat. Fusce imperdiet nulla sit amet porta dignissim. Morbi id odio sodales, malesuada velit sit amet, mollis turpis. Ut consequat lacinia urna convallis maximus. Aliquam arcu velit, cursus eget tempor vitae, varius at mauris. Fusce eu efficitur nisi. Proin blandit arcu vel placerat laoreet. Cras eget venenatis nibh.

                In facilisis diam ac erat malesuada, a molestie enim semper. Sed justo dui, ullamcorper non ante sit amet, ullamcorper venenatis augue. Ut sodales pulvinar urna. Curabitur dignissim mi quis pharetra iaculis. Mauris efficitur convallis tempor. Sed aliquet ante et augue conseq"""));
    }

    @Test
    public void testEmptyInstrument() {
        assertFalse(musicValidationService.validateInstrument(null));
    }

    @Test
    public void testValidInstrument() {
        assertTrue(musicValidationService.validateInstrument("Agogo"));
    }

    @Test
    public void testInvalidBPMType() {
        assertEquals(-1, musicValidationService.parseBPM("this can't be transformed to int"));
    }

    @Test
    public void testInvalidBPMRange() {
        assertEquals(-1, musicValidationService.parseBPM("39"));
        assertEquals(-1, musicValidationService.parseBPM("225"));
    }

    @Test
    public void testValidBPMInput(){
        assertEquals(40, musicValidationService.parseBPM("40"));
        assertEquals(220, musicValidationService.parseBPM("220"));
        assertEquals(100, musicValidationService.parseBPM("100"));
    }
}
